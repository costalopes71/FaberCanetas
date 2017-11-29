package br.com.fabercanetas.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabercanetas.bo.PedidoVendaBO;
import br.com.fabercanetas.exceptions.DAOException;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.to.Cliente;
import br.com.fabercanetas.to.PedidoVenda;

/**
 * Servlet implementation class PedidoController
 */
@WebServlet("/meuspedidos")
public class PedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidoController() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opt = request.getParameter("opt");
		RequestDispatcher fw = null;
		
		try {
			switch (opt) {
			case "exibirPedidos":
				Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
				request.setAttribute("listaPedido", (new PedidoController()).exibirPedidos(cliente, response));
				fw = request.getRequestDispatcher("gerenciamento_pedido.jsp");
				fw.forward(request, response);
				break;
			default:
				break;
			}
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}

	private ArrayList<PedidoVenda> exibirPedidos(Cliente cliente, HttpServletResponse response) throws RetrieveException {
		ArrayList<PedidoVenda> listaPedido = null; 
		listaPedido = (new PedidoVendaBO().listarPedidosCliente(cliente.getCodigo()));
		return listaPedido;
	}
	
}
