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
 * Servlet implementation class Servlet
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 5774170106592807884L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher fw = null;

		String opt = request.getParameter("opt");
		if (opt == null || opt.equals("")) {
			opt = "1";
		}

		try {
			switch (opt) {
			case "1":
				request.setAttribute("attrListaProduto", new ProdutoBO().listaProduto(4, 6));
				fw = request.getRequestDispatcher("index.jsp");
				fw.forward(request, response);
				break;
			case "2":
				fw = request.getRequestDispatcher("login.jsp");
				fw.forward(request, response);
				break;
			case "3":
				fw = request.getRequestDispatcher("carrinho.jsp");
				fw.forward(request, response);
				break;
			case "99":
				request.getSession().invalidate();
				fw = request.getRequestDispatcher("login.jsp");
				fw.forward(request, response);
			default:
				break;
			}
		} catch (DAOException e) {
			throw new ServletException(e);
		}
		
	}

}
