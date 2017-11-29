package br.com.fabercanetas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabercanetas.bo.ItemVendaBO;
import br.com.fabercanetas.bo.PedidoVendaBO;
import br.com.fabercanetas.bo.ProdutoBO;
import br.com.fabercanetas.exceptions.DAOException;
import br.com.fabercanetas.exceptions.InsertException;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.to.Cliente;
import br.com.fabercanetas.to.Funcionario;
import br.com.fabercanetas.to.ItemCarrinho;
import br.com.fabercanetas.to.ItemVenda;
import br.com.fabercanetas.to.PedidoVenda;
import br.com.fabercanetas.to.Produto;
import br.com.fabercanetas.utils.Utils;

/**
 * Servlet implementation class CarrinhoController
 */
@WebServlet("/carrinho")
public class CarrinhoController extends HttpServlet {
	private static final long serialVersionUID = -1502302287055081191L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarrinhoController() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HashMap<Integer, ItemCarrinho> carrinhoCliente = new HashMap<Integer, ItemCarrinho>();
		RequestDispatcher fw = null;
		int opt = Integer.parseInt(request.getParameter("opt"));

		try {
			switch (opt) {
			case 1:
				adicionarItem(request, response, carrinhoCliente);
				fw = request.getRequestDispatcher("carrinho.jsp");
				fw.forward(request, response);
				break;
			case 2:
				limparCarrinho(request, carrinhoCliente);
				fw = request.getRequestDispatcher("carrinho.jsp");
				fw.forward(request, response);
				break;
			case 3:
				removerItem(request, response, carrinhoCliente);
				fw = request.getRequestDispatcher("carrinho.jsp");
				fw.forward(request, response);
				break;
			case 4:
				atualizarQtd(request, response, carrinhoCliente);
				break;
			case 5:
				atualizarSubtotal(request, response, carrinhoCliente);
				break;
			case 6:
				finalizarCompra(request, response, carrinhoCliente);
				fw = request.getRequestDispatcher("confirma_compra.jsp");
				fw.forward(request, response);
				break;
			default:
				break;
			}
		} catch (DAOException e) {
			throw new ServletException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private static void adicionarItem(HttpServletRequest request, HttpServletResponse response, HashMap<Integer, ItemCarrinho> carrinhoCliente) throws RetrieveException {
		Produto item = new Produto();
		ItemCarrinho itemCarrinho = new ItemCarrinho();
		int idProduto = Integer.parseInt(request.getParameter("idProduto"));
		item = (new ProdutoBO()).buscarProduto(idProduto);
		itemCarrinho.setNomeProduto(item.getNomeProduto());
		itemCarrinho.setDescricaoProdutoCompleta(item.getDescricaoProdutoCompleta());
		itemCarrinho.setCodigo(item.getCodigo());
		itemCarrinho.setValorUnitario(item.getValorUnitario());
		itemCarrinho.setUrlImgProduto(item.getUrlImgProduto());
		itemCarrinho.setDescricaoSimples(item.getDescricaoSimples());
		itemCarrinho.setValorTotalItem(item.getValorUnitario());
		carrinhoCliente = (HashMap<Integer, ItemCarrinho>) request.getSession().getAttribute("carrinhoCliente");
		carrinhoCliente.put(itemCarrinho.getCodigo(), itemCarrinho);
		request.getSession().setAttribute("carrinhoCliente", carrinhoCliente);
	}
	
	@SuppressWarnings("unchecked")
	private static void limparCarrinho(HttpServletRequest request, HashMap<Integer, ItemCarrinho> carrinhoCliente) {
		carrinhoCliente = (HashMap<Integer, ItemCarrinho>) request.getSession().getAttribute("carrinhoCliente");
		carrinhoCliente.clear();
		request.getSession().setAttribute("carrinhoCliente", carrinhoCliente);
	}
	
	@SuppressWarnings("unchecked")
	private static void removerItem(HttpServletRequest request, HttpServletResponse response, HashMap<Integer, ItemCarrinho> carrinhoCliente) {
		int idProduto = Integer.parseInt(request.getParameter("idProduto"));
		carrinhoCliente = (HashMap<Integer, ItemCarrinho>) request.getSession().getAttribute("carrinhoCliente");
		carrinhoCliente.remove(idProduto);
		request.getSession().setAttribute("carrinhoCliente", carrinhoCliente);
	}
	
	@SuppressWarnings("unchecked")
	private static void atualizarQtd(HttpServletRequest request, HttpServletResponse response, HashMap<Integer, ItemCarrinho> carrinhoCliente) throws IOException {
		int idItem = Integer.parseInt(request.getParameter("id"));
		int qtdItem = Integer.parseInt(request.getParameter("qtd"));
		carrinhoCliente = (HashMap<Integer, ItemCarrinho>) request.getSession().getAttribute("carrinhoCliente");
		carrinhoCliente.get(idItem).setQtd(qtdItem);
		request.getSession().setAttribute("carrinhoCliente", carrinhoCliente);
		response.getWriter().write(String.valueOf(carrinhoCliente.get(idItem).getValorTotalItem()));
	}
	
	@SuppressWarnings("unchecked")
	private static void atualizarSubtotal(HttpServletRequest request, HttpServletResponse response, HashMap<Integer, ItemCarrinho> carrinhoCliente) throws IOException {
		carrinhoCliente = (HashMap<Integer, ItemCarrinho>) request.getSession().getAttribute("carrinhoCliente");
		double subtotal = 0;
		for (ItemCarrinho itemCart : carrinhoCliente.values()) {
			subtotal += itemCart.getValorTotalItem();
		}
		response.getWriter().write(String.valueOf(subtotal));
	}
	
	@SuppressWarnings("unchecked")
	private static void finalizarCompra(HttpServletRequest request, HttpServletResponse response, HashMap<Integer, ItemCarrinho> carrinhoCliente) throws IOException, InsertException, RetrieveException {
		
		//variaveis locais do metodo.
		int codigoPedido = 0;
		Utils util = new Utils();
		
		// Pegando o Cliente que esta fazendo o pedido e o funcionario
		Cliente usuario = (Cliente) request.getSession().getAttribute("usuario");
		Funcionario funcionario = (Funcionario) request.getSession().getAttribute("funcionario");
		// Pegando o carrinho do Cliente
		carrinhoCliente = (HashMap<Integer, ItemCarrinho>) request.getSession().getAttribute("carrinhoCliente");
		// Setando o objeto PedidoVenda com os dados do pedido de venda
		PedidoVenda pedido = new PedidoVenda();
		pedido.setCliente(usuario);
		pedido.setFuncionario(funcionario);
		pedido.setDataPedido(util.getLocalDateFromString());
		pedido.setStatusPedido("P");
		pedido.setPrazoEntrega(util.createDeadline());
		pedido.setValorFrete(util.createFreight());
		// GRAVANDO O PEDIDO DE VENDA NO BD.
		codigoPedido = new PedidoVendaBO().inserirPedidoVenda(pedido);
		// PASSANDO O CODIGO DO PEDIDO GERADO PELA SEQUENCE NO BD PARA O OBJETO PedidoVenda
		pedido.setCodigo(codigoPedido);

		// PONDO OS ITENS DO CARRINHO DA SESSAO EM UMA COLECAO DE ItemVenda
		ArrayList<ItemVenda> listaItem = new ArrayList<>();
		for (ItemCarrinho itemCart : carrinhoCliente.values()) {
			ItemVenda itemVenda = new ItemVenda();
			Produto produto = new Produto();
			produto = new ProdutoBO().buscarProduto(itemCart.getCodigo());
			itemVenda.setProduto(produto);
			itemVenda.setPedido(pedido);
			itemVenda.setQuantidade(itemCart.getQtd());
			itemVenda.setValorTotalItem(itemCart.getValorTotalItem());
			itemVenda.setValorUnitario(produto.getValorUnitario());
			listaItem.add(itemVenda);
		}
		// GRAVANDO OS ITENS QUE FORAM ADICIONADOS NA COLECAO NO BANCO DE DADOS
		carrinhoCliente.clear();
		pedido.setListaItem(listaItem);
		(new ItemVendaBO()).inserirColecao(listaItem);
		request.setAttribute("pedido", pedido);
		request.getSession().setAttribute("carrinhoCliente", carrinhoCliente);
	}

}
