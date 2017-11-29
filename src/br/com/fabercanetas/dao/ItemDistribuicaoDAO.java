package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabercanetas.to.ItemDistribuicao;
import br.com.fabercanetas.to.PedidoDistribuicao;

/**
 * Classe que persiste os dados dos itens do pedido de distribuicao no banco de dados.
 * @author TDS Tecnologia
 * @version 1.0
 */
public class ItemDistribuicaoDAO {

	/**
	 * Método que insere um item de distribuicao no Banco de Dados. Espera por um parametro item que é um objeto ItemDistribuicao.
	 * @param item ItemDistribuicao
	 * @throws SQLException
	 */
	public void inserirItem(ItemDistribuicao item, int codigoDeposito) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			
			String query = "SELECT E.CD_ITEM_ESTOQUE FROM T_TDS_ESTOQUE E JOIN T_TDS_ESTOQUE_PRODUTO P ON E.CD_ITEM_ESTOQUE = "
					+ "P.CD_ITEM_ESTOQUE JOIN T_TDS_DEPOSITO D ON D.CD_DEPOSITO = E.CD_DEPOSITO WHERE P.cd_produto = ? AND "
					+ "D.cd_deposito = ?";
			PreparedStatement pstm2 = conn.prepareStatement(query);
			pstm2.setInt(1, item.getProduto().getCodigo());
			pstm2.setInt(2, codigoDeposito);
			ResultSet rs2 = pstm2.executeQuery();
			
			while (rs2.next()) {
				item.setCodigoItemEstoque(rs2.getInt("cd_item_estoque"));
			}
			
			String sql = "INSERT INTO T_TDS_ITEM_DISTRIBUICAO VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, item.getPedido().getCodigo());
			pstm.setInt(2, item.getQuantidade());
			pstm.setInt(3, item.getCodigoItemEstoque());
			pstm.setInt(4, item.getProduto().getCodigo());
			pstm.setDouble(5, item.getValorUnitario());
			pstm.executeUpdate();
			
			String baixaEstoque = "UPDATE T_TDS_ESTOQUE SET qt_produto = qt_produto - ? WHERE cd_item_estoque = ?";
			PreparedStatement pstm3 = conn.prepareStatement(baixaEstoque);
			pstm3.setInt(1, item.getQuantidade());
			pstm3.setInt(2, item.getCodigoItemEstoque());
			pstm3.executeUpdate();
			
			pstm.close();
			rs2.close();
			pstm2.close();
			pstm3.close();
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o banco de dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que retorna um ArrayList com todos os itens de distribuicao de um determinado pedido de distribuicao. Para tanto é necessário passar
	 * como parametro o número do pedido de distribuicao que se quer recuperar os items.
	 * @param codigoDistribuicao, número inteiro que representa o codigo do pedido de distribuicao.
	 * @return listaItem , um ArrayList<ItemDistribuicao> o qual possui todos os itens do pedido de distribuicao.
	 * @throws SQLException
	 */
	public List<ItemDistribuicao> listarItensDistribuicao(int codigoDistribuicao) throws SQLException {
		
		Connection conn = null;
		ItemDistribuicao item = null;
		List<ItemDistribuicao> listaItem = new ArrayList<>();
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_ITEM_DISTRIBUICAO WHERE cd_pedido_distribuicao = ?";
			
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoDistribuicao);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				item = new ItemDistribuicao();
				PedidoDistribuicao pd = new PedidoDistribuicao();
				pd.setCodigo(rs.getLong("cd_pedido_distribuicao"));
				item.setPedido(pd);
				item.setQuantidade(rs.getInt("qt_produto"));
				item.setCodigoItemEstoque(rs.getInt("cd_item_estoque"));
				item.setProduto(new ProdutoDAO().buscarProduto(rs.getInt("cd_produto")));
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
	 * Método que retorna um ArrayList com todos os itens de distribuicao de um determinado pedido de distribuicao. Para tanto é necessário passar
	 * como parametro o número do pedido de distribuicao que se quer recuperar os items.
	 * @param codigoDistribuicao, número inteiro que representa o codigo do pedido de distribuicao.
	 * @param Connection conn
	 * @return listaItem , um ArrayList<ItemDistribuicao> o qual possui todos os itens do pedido de distribuicao.
	 * @throws SQLException
	 */
	public List<ItemDistribuicao> listarItensDistribuicao(int codigoDistribuicao, Connection conn) throws SQLException {
		
		ItemDistribuicao item = null;
		List<ItemDistribuicao> listaItem = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM T_TDS_ITEM_DISTRIBUICAO WHERE cd_pedido_distribuicao = ?";
			
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoDistribuicao);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				item = new ItemDistribuicao();
				PedidoDistribuicao pd = new PedidoDistribuicao();
				pd.setCodigo(rs.getLong("cd_pedido_distribuicao"));
				item.setPedido(pd);
				item.setQuantidade(rs.getInt("qt_produto"));
				item.setCodigoItemEstoque(rs.getInt("cd_item_estoque"));
				item.setProduto(new ProdutoDAO().buscarProduto(rs.getInt("cd_produto")));
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
	
}
