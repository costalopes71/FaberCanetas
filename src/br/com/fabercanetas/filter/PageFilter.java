package br.com.fabercanetas.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabercanetas.bo.ClienteBO;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.to.Cliente;
import br.com.fabercanetas.to.Funcionario;
import br.com.fabercanetas.to.ItemCarrinho;

/**
 * Classe de filtro que filtra todas as requisicoes sejam elas requisicoes para urls ou servlets, e se
 * não houver um cliente ou funcionario logado validamente redireciona essas requisicoes para a pagina
 * de login.
 * @author TDS Tecnologia
 * @version 1.2
 *
 */
public class PageFilter implements Filter {
	private ClienteBO clienteBO;
	
	public void init(FilterConfig arg0) throws ServletException {
		clienteBO = new ClienteBO();
	}
	
	/**
	 * Função relevante apenas durante tempo de DEV.
	 * Enquanto configuração estiver definida como 'DEV', pula tela de login
	 * @param request
	 * @return
	 */
	private String GetDestinationPage(HttpServletRequest request) {
		InputStream settings = request.getServletContext().getResourceAsStream("/WEB-INF/settings.properties");
		Properties properties = new Properties();
		try {
			properties.load(settings);;
			String loginUser = properties.getProperty("loginUser");
			if( !loginUser.equals("")) {
				try {
					request.getSession().setAttribute("usuario", clienteBO.buscarCliente(loginUser));
				} catch (RetrieveException e) {
					e.printStackTrace();
				}
				request.getSession().setAttribute("carrinhoCliente", new HashMap<Integer, ItemCarrinho>());
				return "home";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "login.jsp";
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		Object usuario = null;
		
		if (session.getAttribute("usuario") instanceof Cliente) {
			usuario = (Cliente) session.getAttribute("usuario");
		} else {
			usuario = (Funcionario) session.getAttribute("usuario");
		}
		
		if (usuario == null) {
			String destino = GetDestinationPage((HttpServletRequest)request);
			((HttpServletResponse)response).sendRedirect(destino);
		} else {
			chain.doFilter(request, response);
		}

	}
	
	public void destroy() {}
}
