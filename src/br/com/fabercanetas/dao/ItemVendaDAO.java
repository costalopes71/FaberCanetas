package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fabercanetas.to.ItemVenda;
import br.com.fabercanetas.to.PedidoVenda;
import br.com.fabercanetas.to.RelatorioItem;

/**
 * Classe que persiste os dados dos itens do pedido de venda no banco de dados.
 * @author TDS Tecnologia
 * @version 1.1
 */
public class ItemVendaDAO {

	/**
	 * Método que insere um item de venda e sua respectiva quantidade e valor total no Banco de Dados. Espera por um parametro item que
	 * é um objeto ItemVenda.
	 * @param item ItemVenda
	 * @throws SQLException
	 */
	public void inserirItem(ItemVenda item) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_TDS_ITEM_VENDA VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, item.getPedido().getCodigo());
			pstm.setInt(2, item.getQuantidade());
			pstm.setDouble(3, item.getValorUnitario());
			pstm.setInt(4, item.getProduto().getCodigo());
			pstm.setDouble(5, item.getValorTotalItem());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o banco de dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que insere vários itens de venda no banco de dados. Espera como parametro um ArrayList contendo os items de venda (ItemVenda)
	 * @param listaItem ArrayList<ItemVenda>
	 * @throws SQLException
	 */
	public void inserirColecao(ArrayList<ItemVenda> listaItem) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			
			for (int i = 0; i < listaItem.size(); i++) {
				this.inserirItem(listaItem.get(i));
			}
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o banco de dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que retorna um item de determinado pedido de venda. Para tanto é necessário como parametro o código do pedido de venda
	 * e o código do produto.
	 * @param codigoPedido, numero inteiro que representa o codigo do pedido de venda.
	 * @param codigoProduto, numero inteiro que representa o codigo do produto.
	 * @return item ItemVenda, retorna um unico item de venda.
	 * @throws SQLException
	 */
	public ItemVenda buscarItemVenda(int codigoPedido, int codigoProduto) throws SQLException {
		
		Connection conn = null;
		ItemVenda item = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_ITEM_VENDA WHERE cd_pedido_venda = ? AND cd_produto = ?";
			
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoPedido);
			pstm.setInt(2, codigoProduto);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				item = ItemVendaDAO.setItemVendaFromBD(item, rs);
			}
			
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o banco de dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return item;
	}
	
	/**
	 * Método que retorna um ArrayList com todos os itens de venda de um determinado pedido de venda. Para tanto é necessário passar
	 * como parametro o número do pedido de venda que se quer recuperar os items.
	 * @param codigoVenda, número inteiro que representa o codigo de venda.
	 * @return listaItem , um ArrayList<ItemVenda> o qual possui todos os itens do pedido de venda.
	 * @throws SQLException
	 */
	public ArrayList<ItemVenda> listarItensVenda(int codigoVenda) throws SQLException {
		
		Connection conn = null;
		ItemVenda item = null;
		ArrayList<ItemVenda> listaItem = new ArrayList<>();
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_ITEM_VENDA WHERE cd_pedido_venda = ?";
			
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoVenda);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				item = ItemVendaDAO.setItemVendaFromBD(item, rs);
				listaItem.add(item);
			}
			
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o banco de dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return listaItem;
	}
	
	/**
	 * Método que retorna um ArrayList com todos os itens de venda de um determinado pedido de venda. Para tanto é necessário passar
	 * como parametro o número do pedido de venda que se quer recuperar os items.
	 * @param codigoVenda, número inteiro que representa o codigo de venda.
	 * @param Connection conn (um objeto conexao ja aberto)
	 * @return listaItem , um ArrayList<ItemVenda> o qual possui todos os itens do pedido de venda.
	 * @throws SQLException
	 */
	public ArrayList<ItemVenda> listarItensVenda(int codigoVenda, Connection conn) throws SQLException {
		
		ItemVenda item = null;
		ArrayList<ItemVenda> listaItem = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM T_TDS_ITEM_VENDA WHERE cd_pedido_venda = ?";
			
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoVenda);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				item = new ItemVenda();
				PedidoVenda p = new PedidoVenda();
				p.setCodigo(rs.getInt("cd_pedido_venda"));
				item.setPedido(p);
				item.setProduto(new ProdutoDAO().buscarProduto(rs.getInt("cd_produto")));
				item.setQuantidade(rs.getInt("qt_produto"));
				item.setValorTotalItem(rs.getDouble("vl_total"));
				item.setValorUnitario(rs.getDouble("vl_produto"));
				listaItem.add(item);
			}
			
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o banco de dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return listaItem;
	}
	
	/**
	 * Método que atualiza um item de venda. Para tanto necessita como parametro um objeto ItemVenda que possua as informacoes do
	 * item a ser alterado.
	 * @param item , objeto ItemVenda com os dados a serem alterados.
	 * @throws SQLException
	 */
	public void atualizarItemVenda(ItemVenda item) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_TDS_ITEM_VENDA SET qt_produto = ?, vl_produto = ?, vl_total = ? WHERE cd_pedido_venda = ? AND cd_produto";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, item.getQuantidade());
			pstm.setDouble(2, item.getValorUnitario());
			pstm.setDouble(3, item.getValorTotalItem());
			pstm.setInt(4, item.getPedido().getCodigo());
			pstm.setInt(5, item.getProduto().getCodigo());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o banco de dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
	}
	
	/**
	 * Método que deleta um item de venda do banco de dados. Para tanto espera como parametro um numero inteiro que representa o codigo
	 * do pedido de venda e um numero inteiro que representa o codigo do produto.
	 * @param codigoPedido , integer que representa o codigo do pedido de venda.
	 * @param codigoProduto , integer que representa o codigo do produto.
	 * @throws SQLException
	 */
	public void deletarItemVenda(int codigoPedido, int codigoProduto) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_TDS_ITEM_VENDA WHERE cd_pedido_venda = ? AND cd_produto = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, codigoPedido);
			pstm.setInt(2, codigoProduto);
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o banco de dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que retorna um ArrayList contendo todos os produtos e suas respectivas quantidades totais vendidas entre
	 * a data de inicio e fim que o usuario indicou.
	 * @param inicio, uma String que representa a data de inicio desejada. 
	 * @param fim, uma String que representa a data de fim desejada. 
	 * @return relatorioMensal, um ArrayList<RelatorioItem> que contem todos os produtos e suas respectivas quantidades
	 * vendidas entre a data de inicio e data de fim.
	 * @throws SQLException
	 */
	public ArrayList<RelatorioItem> relatorioMensal(String inicio, String fim) throws SQLException {
		
		ArrayList<RelatorioItem> relatorioMensal = new ArrayList<>();
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT P.nm_produto, SUM(IV.qt_produto) AS QTDTOTAL FROM T_TDS_PEDIDO_VENDA PV JOIN T_TDS_ITEM_VENDA IV "
					+ "ON PV.cd_pedido_venda = IV.cd_pedido_venda JOIN T_TDS_PRODUTO P ON IV.cd_produto = P.cd_produto "
					+ "WHERE PV.dt_pedido BETWEEN to_date(?, 'DD/MM/YYYY') AND to_date(?, 'DD/MM/YYYY') "
					+ "GROUP BY P.nm_produto ORDER BY qtdtotal DESC";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, inicio);
			pstm.setString(2, fim);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				RelatorioItem produto = new RelatorioItem();
				produto.setNomeProduto(rs.getString("nm_produto"));
				produto.setQuantidade(rs.getInt("qtdtotal"));
				relatorioMensal.add(produto);
			}
			pstm.close();
			rs.close();
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o banco de dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return relatorioMensal;
	}
	
	/**
	 * Metodo que retorna um objeto ItemVenda com todas as informacos do item que foi pesquisado no banco de dados e que esta dentro
	 * do ResultSet.
	 * @param item ItemVenda.
	 * @param rs , um objeto ResultSet que contem os dados da query feita no Banco de Dados.
	 * @return item ItemVenda, retorna o item de venda com todos os valores adicionados.
	 * @throws SQLException
	 */
	private static ItemVenda setItemVendaFromBD(ItemVenda item, ResultSet rs) throws SQLException {
		item = new ItemVenda();
		PedidoVenda p = new PedidoVenda();
		p.setCodigo(rs.getInt("cd_pedido_venda"));
		item.setPedido(p);
		item.setProduto(new ProdutoDAO().buscarProduto(rs.getInt("cd_produto")));
		item.setQuantidade(rs.getInt("qt_produto"));
		item.setValorTotalItem(rs.getDouble("vl_total"));
		item.setValorUnitario(rs.getDouble("vl_produto"));
		return item;
	}
	
}
