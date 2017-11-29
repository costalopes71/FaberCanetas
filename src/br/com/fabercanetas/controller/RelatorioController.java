package br.com.fabercanetas.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabercanetas.bo.DepositoBO;
import br.com.fabercanetas.bo.ItemCompraMPBO;
import br.com.fabercanetas.bo.ItemDistribuicaoBO;
import br.com.fabercanetas.bo.OrdemCompraBO;
import br.com.fabercanetas.bo.PedidoDistribuicaoBO;
import br.com.fabercanetas.bo.PedidoVendaBO;
import br.com.fabercanetas.bo.RelatorioBO;
import br.com.fabercanetas.exceptions.DAOException;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.exceptions.UpdateException;
import br.com.fabercanetas.to.Deposito;
import br.com.fabercanetas.to.ItemCompraMP;
import br.com.fabercanetas.to.ItemDistribuicao;
import br.com.fabercanetas.to.ItemEstoque;
import br.com.fabercanetas.to.ItemVenda;
import br.com.fabercanetas.to.OrdemCompra;
import br.com.fabercanetas.to.PedidoDistribuicao;
import br.com.fabercanetas.to.PedidoVenda;
import br.com.fabercanetas.utils.Utils;

/**
 * Servlet implementation class RelatorioController
 */
@WebServlet("/relatorio")
public class RelatorioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RelatorioBO relatorioBO;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelatorioController() {
        super();
        relatorioBO = new RelatorioBO();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String opt = request.getParameter("opt");
		String resultado = null;

		try {
			switch (opt) {
			case "1":
				printRankingCliente(resultado, response);
				break;
			case "2":
				printRankingProduto(resultado, response);
				break;
			case "3":
				printFaturamentoRegiao(resultado, response);
				break;
			case "4":
				printFaturamentoMensal(resultado, response);
				break;
			case "5":
				printEstoqueCentral(resultado, response);
				break;
			case "6":
				printEstoqueNordeste(resultado, response);
				break;
			case "7":
				printEstoqueSul(resultado, response);
				break;
			case "8":
				abrirModalDistribuicao(request, response);
				break;
			case "9":
				abrirModalVenda(request, response);
				break;
			case "10":
				gravarPedidoDistribuicao(request, response);
				break;
			case "11":
				receberOrdemCompra(request, response);
				break;
			case "ticket":
				printTicketMedio(request, response);
				break;
			case "log":
				logarFuncionario(request, response);
				break;
			default:
				break;
			}
		} catch (DAOException e) {
			throw new ServletException(e);
		}
		
	}
	
	private void receberOrdemCompra(HttpServletRequest request, HttpServletResponse response) throws IOException, RetrieveException, UpdateException {
		int codigoOrdemCompra = Integer.parseInt(request.getParameter("cd"));
		OrdemCompraBO ordemCompraBO = new OrdemCompraBO();
		OrdemCompra ordemCompra = ordemCompraBO.buscarOrdemCompra(codigoOrdemCompra);
		
		ItemCompraMPBO itemCompraBO = new ItemCompraMPBO();
		for (ItemCompraMP itemCompra : ordemCompra.getListaItem()) {
			itemCompraBO.atualizarEstoqueMP(itemCompra);
		}
		
		ordemCompra.setStatus("C");
		ordemCompraBO.atualizarStatusOrdemCompra(ordemCompra);
		
		response.getWriter().write("");
	}

	private void gravarPedidoDistribuicao(HttpServletRequest request, HttpServletResponse response) throws RetrieveException, IOException, UpdateException {
		//pegando os paramentros codigo do pedido de venda e codigo do deposito
		int codigoPedidoVenda = Integer.parseInt(request.getParameter("cd"));
		int codigoDeposito = Integer.parseInt(request.getParameter("dep"));
		
		//criando o objeto de venda com o codigo que veio como parametro
		PedidoVendaBO vendaBO = new PedidoVendaBO();
		PedidoVenda pv = vendaBO.buscarPedidoVenda(codigoPedidoVenda);
		
		//criando o objeto pedido de distribuicao com as informacoes do objeto pedido de venda e setando outros atribuos
		PedidoDistribuicao pd = new PedidoDistribuicao();
		pd.setPedidoVenda(pv);
		pd.setDataSolicitacao(new Utils().getLocalDateFromString());
		
		//gravando o pedido de distribuicao no banco e pegando o codigo dele e setando no objeto PedidoDistribuicao criado anteriormente
		int codigoPedidoDistribuicao = new PedidoDistribuicaoBO().inserirPedidoDistribuicao(pd);
		pd.setCodigo(codigoPedidoDistribuicao);
		
		//gravando os Itens de Distribuicao e dando baixa no estoque
		ItemDistribuicaoBO itemBO = new ItemDistribuicaoBO();
		ItemDistribuicao itemDistribuicao = null;
		for (ItemVenda itemVenda : pv.getListaItem()) {
			itemDistribuicao = new ItemDistribuicao();
			itemDistribuicao.setPedido(pd);
			itemDistribuicao.setProduto(itemVenda.getProduto());
			itemDistribuicao.setQuantidade(itemVenda.getQuantidade());
			itemDistribuicao.setValorUnitario(itemVenda.getValorUnitario());
			itemBO.inserirItem(itemDistribuicao, codigoDeposito);
			pd.getListaItem().add(itemDistribuicao);
		}
		
		//atualizando o status do pedido de venda
		pv.setStatusPedido("C");
		vendaBO.atualizarPedidoVenda(pv);
		
		//mandando a resposta para o ajax
		response.getWriter().write("");
	}

	private void abrirModalVenda(HttpServletRequest request, HttpServletResponse response) throws IOException, RetrieveException {
		int codigoPV = Integer.parseInt(request.getParameter("cd"));
		PedidoVenda pv = new PedidoVendaBO().buscarPedidoVenda(codigoPV);
		StringBuilder sb2 = new StringBuilder();
		LocalDate dt = pv.getDataPedido();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataPedido = dt.format(formatter);
		sb2.append("{\"codigo\":").append(pv.getCodigo()).append(", \"cliente\": \"").append(pv.getCliente().getNomeFantasia()).append("\" , \"data\": \"").append(dataPedido).append("\"}");
		String resultadoVenda = sb2.toString();
		response.getWriter().write(resultadoVenda);
	}

	private void abrirModalDistribuicao(HttpServletRequest request, HttpServletResponse response) throws RetrieveException, IOException {
		int codigoOC = Integer.parseInt(request.getParameter("cd"));
		OrdemCompra oc = new OrdemCompraBO().buscarOrdemCompra(codigoOC);
		StringBuilder sb = new StringBuilder();
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataRecebimento = agora.format(formatter);
		sb.append("{\"codigo\":").append(oc.getCodigo()).append(", \"fornecedor\": \"").append(oc.getFornecedor().getNomeFantasia()).append("\" , \"data\": \"").append(dataRecebimento).append("\"}");
		String resposta = sb.toString();
		response.getWriter().write(resposta);
	}

	/**
	 * Metodo que decide o status do farol a ser gravado na sessao do usuario. Este status serve para que a pagina jsp possa pegar o 
	 * status e mudar a cor do farol geral do sistema. Se houver um pedido de venda ou uma ordem de compra atrasadas ou o estoque
	 * de produto de algum dos depositos estiver vazio o status do farol geral do sistema vale vermelho. Se nao houver nenhuma
	 * destas condicoes mas existe um pedido de venda ou uma ordem de compra pendentes ou o estoque de produto de algum dos depositos
	 * estiver abaixo do limite minimo, o status do farol geral do sistema vale amarelo. Se nenhuma dessas condicoes for verdadeira 
	 * ele vale verde.
	 * @since 14/10/2017
	 * @author TDS Tecnologia
	 * @version 1.0B  
	 * @param request, o objeto HttpServletRequest.
	 * @param listaOC, um List<OrdemCompra> contendo as ordens de compra que estao pendentes e atrasadas.
	 * @param listaPedido, um List<PedidoVenda> contendo os pedidos de venda que estao pendentes e atrasados.
	 * @throws ServletException
	 * @throws IOException
	 * @throws RetrieveException 
	 */
	private void printFarol(HttpServletRequest request, List<OrdemCompra> listaOC, List<PedidoVenda> listaPedido) throws ServletException, IOException, RetrieveException {
		
		String statusFarol = "img/verde.gif";
		String statusFarolDep = "img/verdePeq.png";
		String statusFarolPedido = "img/verdePeq.png";
		String statusFarolOC = "img/verdePeq.png";
		
		if (!statusFarol.equals("img/vermelho.gif")) {
			
			ArrayList<Deposito> listaDeposito = new ArrayList<>();
			DepositoBO depositoBo = new DepositoBO();
			Deposito deposito = null;
			
			for (int i = 1; i <= 3; i++) {
				deposito = depositoBo.buscarDeposito(i);
				deposito.setListaProduto(depositoBo.getItems(deposito.getCodigo()));
				listaDeposito.add(deposito);
			}
			
			for (int i = 0; i < listaDeposito.size(); i++) {
				ArrayList<ItemEstoque> listaProduto = listaDeposito.get(i).getListaProduto();
				for (int j = 0; j < listaProduto.size(); j++) {
					if (listaProduto.get(j).getQuantidade() < listaProduto.get(j).getQuantidadeMinima()) {
						statusFarol = "img/vermelho.gif";
						statusFarolDep = "img/vermelhoPeq.png";
						j = listaProduto.size();
						i = listaDeposito.size();
					} else if (listaProduto.get(j).getQuantidade() < listaProduto.get(j).getQuantidadeMinima() + 500) {
						statusFarol = "img/amarelo.gif";
						statusFarolDep = "img/amareloPeq.png";
					}
				}
			}
		}

		if (statusFarolPedido.equals("img/verdePeq.png")) {
			for (int i = 0; i < listaPedido.size(); i++) {
				if (listaPedido.get(i).getStatusPedido().equals("A")) {
					statusFarolPedido = "img/vermelhoPeq.png";
					i = listaPedido.size();
				} else if (listaPedido.get(i).getStatusPedido().equals("P")) {
					statusFarolPedido = "img/amareloPeq.png";
				}
			}
		}
		
		
		if (!statusFarol.equals("img/vermelho.gif")) {
			for (int i = 0; i < listaPedido.size(); i++) {
				if (listaPedido.get(i).getStatusPedido().equals("A")) {
					statusFarol = "img/vermelho.gif";
					i = listaPedido.size();
				} else if (listaPedido.get(i).getStatusPedido().equals("P")) {
					statusFarol = "img/amarelo.gif";
				}
			}
		}
				
		if (statusFarolOC.equals("img/verdePeq.png")) {
			for (int i = 0; i < listaOC.size(); i++) {
				if (listaOC.get(i).getStatus().equals("A")) {
					statusFarolOC = "img/vermelhoPeq.png";
					i = listaOC.size();
				} else if (listaOC.get(i).getStatus().equals("P")) {
					statusFarolOC = "img/amareloPeq.png";
				}
			}
		}
		
		if (!statusFarol.equals("img/vermelho.gif")) {
			for (int i = 0; i < listaOC.size(); i++) {
				if (listaOC.get(i).getStatus().equals("A")) {
					statusFarol = "img/vermelho.gif";
					i = listaOC.size();
				} else if (listaOC.get(i).getStatus().equals("P")) {
					statusFarol = "img/amarelo.gif";
				}
			}
		}
		
		request.getSession().setAttribute("statusFarol", statusFarol);
		request.getSession().setAttribute("statusFarolDep", statusFarolDep);
		request.getSession().setAttribute("statusFarolOC", statusFarolOC);
		request.getSession().setAttribute("statusFarolPedido", statusFarolPedido);
	
	}

	private void logarFuncionario(HttpServletRequest request, HttpServletResponse response) throws RetrieveException, ServletException, IOException {
		List<OrdemCompra> listaOC = new OrdemCompraBO().listarPedidosEmAberto();
		List<PedidoVenda> listaPedido = new PedidoVendaBO().listarPedidosEmAberto();
		request.getSession().setAttribute("listaOC", listaOC);
		request.getSession().setAttribute("listaPV", listaPedido);
		printFarol(request, listaOC, listaPedido);
		RequestDispatcher fw = request.getRequestDispatcher("dashboard_funcionario.jsp");
		fw.forward(request, response);
	}

	private void printFaturamentoMensal(String resultado, HttpServletResponse response) throws RetrieveException, IOException {
		resultado = relatorioBO.getFaturamentoMensal();
		response.getWriter().write(resultado);
	}

	private void printFaturamentoRegiao(String resultado, HttpServletResponse response) throws RetrieveException, IOException {
		resultado = relatorioBO.getFaturamentoRegiao();
		response.getWriter().write(resultado);
	}

	private void printRankingProduto(String resultado, HttpServletResponse response) throws RetrieveException, IOException {
		resultado = relatorioBO.getRankingProduto();
		response.getWriter().write(resultado);
	}

	private void printRankingCliente(String resultado, HttpServletResponse response) throws RetrieveException, IOException {
		resultado = relatorioBO.getRankingCliente();
		response.getWriter().write(resultado);
	}

	private void printTicketMedio(HttpServletRequest request, HttpServletResponse response) throws RetrieveException, ServletException, IOException {
		request.getSession().setAttribute("listaTicket", (new RelatorioBO().getTicketMedio()));
		RequestDispatcher fw = request.getRequestDispatcher("dashboard_gerente.jsp");
		fw.forward(request, response);
		
	}

	private void printEstoqueSul(String resultado, HttpServletResponse response) throws IOException, RetrieveException {
		resultado = relatorioBO.getEstoque(2);
		response.getWriter().write(resultado);
	}

	private void printEstoqueNordeste(String resultado, HttpServletResponse response) throws IOException, RetrieveException {
		resultado = relatorioBO.getEstoque(1);
		response.getWriter().write(resultado);
	}

	private void printEstoqueCentral(String resultado, HttpServletResponse response) throws IOException, RetrieveException {
		resultado = relatorioBO.getEstoque(3);
		response.getWriter().write(resultado);
	}

	//CODIGO COMENTADO PQ NAO FAZ MAIS SENTIDO TER ESSA FUNCIONALIDADE DENTRO DO PORTAL DE VENDA DO CLIENTE
//	String dataInicio = request.getParameter("from");
//	String dataFim = request.getParameter("to");
//	RequestDispatcher rw = null;
//	
//	ArrayList<RelatorioItem> itensRelatorio = new ArrayList<>();
//	try {
//		itensRelatorio = new ItemVendaBO().relatorioMensal(dataInicio, dataFim);
//		request.setAttribute("attrListaRelatorio", itensRelatorio);
//		rw = request.getRequestDispatcher("relatorio_venda.jsp");
//		rw.forward(request, response);
//	} catch (DAOException e) {
//		throw new ServletException(e);
//	}
	
}
