package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabercanetas.to.ItemCompraMP;
import br.com.fabercanetas.to.OrdemCompra;

public class ItemCompraMPDAO {

	/**
	 * Método que retorna um ArrayList com todos os itens de ordem de compra de uma determinada ordem de compra. 
	 * Para tanto é necessário passar como parametro o número da ordem de compra que se quer recuperar os items.
	 * @param codigoOrdemCompra, número inteiro que representa o codigo da ordem de compra.
	 * @return listaItem , um ArrayList<ItemCompraMPDAO> o qual possui todos os itens da ordem de compra.
	 * @throws SQLException
	 */
	public List<ItemCompraMP> listarItensOrdemCompra(int codigoOrdemCompra, Connection conn) throws SQLException {
		
		ItemCompraMP item = null;
		List<ItemCompraMP> listaItem = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM T_TDS_ITEM_OR_COMPRA WHERE cd_ordem_compra = ?";
			
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoOrdemCompra);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				item = new ItemCompraMP();
				OrdemCompra oc = new OrdemCompra();
				oc.setCodigo(rs.getInt("cd_ordem_compra"));
				item.setOrdemCompra(oc);
				item.setValorUnitario(rs.getDouble("vl_produto"));
				item.setQuantidade(rs.getInt("qt_produto"));
				item.setCodigoItemEstoque(rs.getInt("cd_item_estoque"));
				item.setProduto(new ProdutoDAO().buscarProduto(rs.getInt("cd_produto")));
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
	
	public void atualizarEstoqueMP(ItemCompraMP itemCompra) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String atualizacao = "UPDATE T_TDS_ESTOQUE SET qt_produto = qt_produto - ? WHERE cd_item_estoque = ?";
			PreparedStatement pstm = conn.prepareStatement(atualizacao);
			pstm.setInt(1, itemCompra.getQuantidade());
			pstm.setInt(2, itemCompra.getCodigoItemEstoque());
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
	
}
