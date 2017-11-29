package br.com.fabercanetas.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabercanetas.bo.ProdutoBO;
import br.com.fabercanetas.exceptions.DAOException;

/**
 * Servlet implementation class ProdutoController
 */
@WebServlet("/produto")
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = -3665824022844207044L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProdutoController() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher fw = null;
		String opt = request.getParameter("opt");

		try {
			switch (opt) {
			case "1":
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("attrProduto", new ProdutoBO().buscarProduto(id));
				fw = request.getRequestDispatcher("produto.jsp");
				fw.forward(request, response);
				break;
			case "2":
				String nome = request.getParameter("nome");
				request.setAttribute("attrProduto", new ProdutoBO().buscarProduto(nome));
				fw = request.getRequestDispatcher("produto.jsp");
				fw.forward(request, response);
				break;
			default:
				break;
			}
		} catch (DAOException e) {
			throw new ServletException(e);
		}
		
	}

}
