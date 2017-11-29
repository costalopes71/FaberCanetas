package br.com.fabercanetas.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabercanetas.bo.ClienteBO;
import br.com.fabercanetas.bo.FuncionarioBO;
import br.com.fabercanetas.exceptions.DAOException;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.to.Cliente;
import br.com.fabercanetas.to.Funcionario;
import br.com.fabercanetas.to.ItemCarrinho;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 8373957416732588714L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opt = request.getParameter("opt");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		try {
			switch (opt) {
			case "loginCliente":
				logarCliente(request, response, email, senha);
				break;
			case "loginFuncionario":
				logarFuncionario(request, response, email, senha);
				break;
			default:
				break;
			}
		} catch (DAOException e){
			throw new ServletException(e);
		}
	
	}

	private void logarFuncionario(HttpServletRequest request, HttpServletResponse response, String email, String senha) throws IOException, ServletException, RetrieveException {

			Funcionario funcionario = new FuncionarioBO().buscarFuncionario(email, senha);
			
			if (funcionario != null) {
				request.getSession().setAttribute("usuario", funcionario);
				if (funcionario.getFuncao().getNome().equals("Gerente")) {
					response.getWriter().write("trueGerente");
				} else {
					response.getWriter().write("true");
				}
			} else {
				response.getWriter().write("false");
			}
	}

	private void logarCliente(HttpServletRequest request, HttpServletResponse response, String email, String senha) throws IOException, ServletException, RetrieveException {

			Cliente cliente = new ClienteBO().buscarCliente(email, senha);
			
			if (cliente != null) {
				request.getSession().setAttribute("usuario", cliente);
				request.getSession().setAttribute("funcionario", new FuncionarioBO().buscarFuncionario(999999));
				request.getSession().setAttribute("carrinhoCliente", new HashMap<Integer, ItemCarrinho>());
				response.getWriter().write("true");
			} else {
				response.getWriter().write("false");
			}
	}		

}
