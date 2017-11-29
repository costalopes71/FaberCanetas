package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabercanetas.to.OrdemCompra;

public class OrdemCompraDAO {

	public List<OrdemCompra> listarPedidosEmAberto() throws SQLException {
		
		Connection conn = null;
		List<OrdemCompra> listaOrdemCompra = null;
		OrdemCompra oc = null;
		
		try {
			listaOrdemCompra = new ArrayList<>();
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_ORDEM_COMPRA WHERE st_ordem_compra = 'P' OR st_ordem_compra = 'A'";
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				oc = new OrdemCompra();
				oc.setCodigo(rs.getInt("cd_ordem_compra"));
				oc.setDataSolicitacao(rs.getDate("dt_solicitacao").toLocalDate());
				oc.setStatus(rs.getString("st_ordem_compra"));
				Date dataRecebimento = rs.getDate("dt_recebimento");
				if (dataRecebimento != null) {
					oc.setDataRecebimento(dataRecebimento.toLocalDate());
				}
				oc.setObservacao(rs.getString("ob_ordem_compra"));
				oc.setPrazoEntrega(rs.getInt("pz_entrega"));
				oc.setSolicitacaoCompra(new SolicitacaoCompraDAO().buscarSolicitacaoCompra(rs.getInt("cd_solicitacao_compra")));
				oc.setFornecedor(new FornecedorDAO().buscarFornecedor(rs.getInt("cd_parceiro")));
				listaOrdemCompra.add(oc);
			}
			
			return listaOrdemCompra;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que atualiza o status de uma ordem de compra no Banco de Dados.
	 * @param ordemCompra (objeto OrdemCompra com as novas informacoes)
	 * @throws SQLException
	 */
	public void atualizarStatusOrdemCompra(OrdemCompra oc) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String update = "UPDATE T_TDS_ORDEM_COMPRA SET st_ordem_compra = ? WHERE cd_ordem_compra = ?";
			PreparedStatement pstm = conn.prepareStatement(update);
			pstm.setString(1, oc.getStatus());
			pstm.setInt(2, oc.getCodigo());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados.", e);
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	public OrdemCompra buscarOrdemCompra(int codigoOrdem) throws SQLException {
		
		Connection conn = null;
		OrdemCompra ordemCompra = new OrdemCompra();
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String update = "SELECT * FROM T_TDS_ORDEM_COMPRA WHERE cd_ordem_compra = ?";
			PreparedStatement pstm = conn.prepareStatement(update);
			pstm.setInt(1, codigoOrdem);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				ordemCompra.setCodigo(codigoOrdem);
				ordemCompra.setDataSolicitacao(rs.getDate("dt_solicitacao").toLocalDate());
				ordemCompra.setStatus(rs.getString("st_ordem_compra"));
				Date dataRecebimento = rs.getDate("dt_recebimento");
				if (dataRecebimento != null) {
					ordemCompra.setDataRecebimento(dataRecebimento.toLocalDate());
				}
				ordemCompra.setObservacao(rs.getString("ob_ordem_compra"));
				ordemCompra.setPrazoEntrega(rs.getInt("pz_entrega"));
				ordemCompra.setSolicitacaoCompra(new SolicitacaoCompraDAO().buscarSolicitacaoCompra(rs.getInt("cd_solicitacao_compra")));
				ordemCompra.setFornecedor(new FornecedorDAO().buscarFornecedor(rs.getInt("cd_parceiro")));
			}
			ordemCompra.setListaItem(new ItemCompraMPDAO().listarItensOrdemCompra(codigoOrdem, conn));
			
			rs.close();
			pstm.close();
			return ordemCompra;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados.", e);
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
	}
	
}
