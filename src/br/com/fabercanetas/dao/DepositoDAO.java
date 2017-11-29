package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fabercanetas.to.Deposito;
import br.com.fabercanetas.to.ItemEstoque;

public class DepositoDAO {

	public Deposito buscarDeposito(int codigoDeposito) throws SQLException {
		
		Connection conn = null;
		Deposito deposito = new Deposito();
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_DEPOSITO WHERE cd_deposito = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoDeposito);
			ResultSet rs = pstm.executeQuery();
			
			deposito.setCodigo(codigoDeposito);
			while (rs.next()) {
				deposito.setNome(rs.getString("nm_deposito"));
				deposito.setCep(rs.getInt("nr_cep"));
			}
			
			pstm.close();
			rs.close();
			return deposito;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public ArrayList<ItemEstoque> getItems(int codigoDeposito) throws SQLException {
		
		Connection conn = null;
		ArrayList<ItemEstoque> listaItem = new ArrayList<>();
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			ItemEstoque item = null;
			String query = "SELECT E.CD_ITEM_ESTOQUE, E.QT_PRODUTO, E.QT_MINIMA, MPP.DS_PRODUTO FROM T_TDS_ESTOQUE E JOIN T_TDS_ESTOQUE_PRODUTO P ON E.CD_ITEM_ESTOQUE = P.CD_ITEM_ESTOQUE JOIN T_TDS_MP_PRODUTO "
					+ "MPP ON MPP.CD_PRODUTO = P.CD_PRODUTO JOIN T_TDS_DEPOSITO D ON D.CD_DEPOSITO = E.CD_DEPOSITO where D.CD_DEPOSITO = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, codigoDeposito);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				item = new ItemEstoque();
				item.setCodigo(rs.getInt("cd_item_estoque"));
				item.setQuantidade(rs.getLong("qt_produto"));
				item.setQuantidadeMinima(rs.getLong("qt_minima"));
				item.setProduto(rs.getString("ds_produto"));
				listaItem.add(item);
			}
			
			ps.close();
			rs.close();
			return listaItem;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
