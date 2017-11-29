package br.com.fabercanetas.bo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import br.com.fabercanetas.dao.PedidoVendaDAO;
import br.com.fabercanetas.exceptions.DeleteException;
import br.com.fabercanetas.exceptions.InsertException;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.exceptions.UpdateException;
import br.com.fabercanetas.to.PedidoVenda;

/**
 * Classe que persiste os dados do Pedido de Venda no Banco de Dados e trata as regras de negocio. Faz o CRUD completo.
 * @author TDS Tecnologia
 *
 */
public class PedidoVendaBO {

	/**
	 * Método que insere um pedido de venda no Banco de Dados, para tanto chama o método inserirPedido da classe de
	 * persistencia (PedidoVendaDAO).
	 * @param pedido PedidoVenda (objeto PedidoVenda a ser inserido no BD)
	 * @throws InsertException
	 */
	public int inserirPedidoVenda(PedidoVenda pedido) throws InsertException {
		
		try {
			return new PedidoVendaDAO().inserirPedidoVenda(pedido);
		} catch (SQLException e) {
			throw new InsertException();
		}
	}
	
	/**
	 * Método que busca um pedido de venda no Banco de Dados e retorna um objeto PedidoVenda com todos os dados.
	 * Para tanto chama o método buscarPedidoVenda da classe de persistencia (PedidoVendaDAO).
	 * @param codPedidoVenda (int : código de venda do pedido a ser buscado)
	 * @return PedidoVenda (objeto PedidoVenda)
	 * @throws RetrieveException
	 */
	public PedidoVenda buscarPedidoVenda(int codPedidoVenda) throws RetrieveException { 
		try {
			return new PedidoVendaDAO().buscarPedidoVenda(codPedidoVenda);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	/**
	 * Método que retorna um ArrayList com todos os pedidos de venda de um cliente. Para tanto utilliza o metodo 
	 * buscarPedidosCliente da classe de persisntencia PedidoVendaDAO.
	 * @param codCliente (codigo do cliente a ser pesquisado os Pedidos)
	 * @return ArrayList<PedidoVenda> (retorna uma coleção com os pedidos)
	 * @throws RetrieveException
	 */
	public ArrayList<PedidoVenda> listarPedidosCliente(int codCliente) throws RetrieveException {
		try {
			return new PedidoVendaDAO().listarPedidosCliente(codCliente);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	/**
	 * Método que atualiza um pedido de venda no Banco de Dados. Para tanto utilliza o metodo atualizarPedidoVenda
	 * da classe de persisntencia PedidoVendaDAO.
	 * @param pedido (objeto PedidoVenda com as novas informacoes)
	 * @throws UpdateException
	 */
	public void atualizarPedidoVenda(PedidoVenda pedido) throws UpdateException {
		try {
			new PedidoVendaDAO().atualizarPedidoVenda(pedido);
		} catch (SQLException e) {
			throw new UpdateException();
		}
	}
	
	/**
	 * Método que deleta um pedido de venda do Banco de Dados, para tanto chama o método deletarPedido da classe de
	 * persistencia (PedidoVendaDAO).
	 * @param codPedidoVenda (codigo do pedido de venda a ser deletado do BD)
	 * @throws DeleteException
	 */
	public void deletarPedidoVenda(int codPedidoVenda) throws DeleteException {
		try {
			new PedidoVendaDAO().deletarPedidoVenda(codPedidoVenda);
		} catch (SQLException e) {
			throw new DeleteException();
		}
	}
	
	public List<PedidoVenda> listarPedidosEmAberto() throws RetrieveException {
		try {
			List<PedidoVenda> listaPedido = new PedidoVendaDAO().listarPedidosEmAberto();
			PedidoVendaDAO aux = new PedidoVendaDAO();
			
			for (int i = 0; i < listaPedido.size(); i++) {
				if (listaPedido.get(i).getStatusPedido().equals("P")) {
					if (ChronoUnit.DAYS.between(listaPedido.get(i).getDataPedido(), LocalDateTime.now()) > listaPedido.get(i).getPrazoEntrega()) {
						listaPedido.get(i).setStatusPedido("A");
						aux.atualizarStatusPedidoVenda(listaPedido.get(i));
					}
				}
			}
			
			return listaPedido;
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
}
