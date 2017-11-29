package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fabercanetas.to.PedidoDistribuicao;

/**
 * Classe responsavel pela persistencia dos dados de um pedido de distribuicao no Banco de Dados.
 * @author TDS Tecnologia
 *
 */
public class PedidoDistribuicaoDAO {

	/**
	 * Método que insere um pedido de distribuicao no Banco de Dados.
	 * @param pedido PedidoDistribuicao (objeto PedidoDistribuicao a ser inserido no BD)
	 * @return codigoDistribuicao (int), representa o codigo do pedido de distribuicao que foi gerado pela sequence no banco. 
	 * @throws SQLException
	 */
	public int inserirPedidoDistribuicao(PedidoDistribuicao pedido) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			
			String sql = "INSERT INTO T_TDS_PEDIDO_DISTRIBUICAO VALUES(SQ_PEDIDO_DISTRIBUICAO.NEXTVAL, ?, ?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql, new String[]{"cd_pedido_distribuicao"});
			pstm.setInt(1, pedido.getPedidoVenda().getCodigo());
			pstm.setDate(2, java.sql.Date.valueOf(pedido.getDataSolicitacao()));
			if (pedido.getDataEnvio() != null) {
				pstm.setDate(3, java.sql.Date.valueOf(pedido.getDataEnvio()));
			} else {
				pstm.setDate(3, null);
			}
			pstm.setString(4, pedido.getObservacao());
			pstm.executeUpdate();
			
			ResultSet rs = pstm.getGeneratedKeys();
			int codigoPedido = 0;
			
			if (rs.next()) {
				codigoPedido = rs.getInt(1);
			}
			
			rs.close();
			pstm.close();
			return codigoPedido;
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que busca um pedido de distribuicao no Banco de Dados e retorna um objeto PedidoDistribuicao com todos os dados
	 * @param int codPedidoDistribuicao (código do pedido a ser buscado)
	 * @return PedidoDistribuicao (objeto PedidoDistribuicao)
	 * @throws SQLException
	 */
	public PedidoDistribuicao buscarPedidoDistribuicao(int codPedidoDistribuicao) throws SQLException {
		
		Connection conn = null;
		PedidoDistribuicao pedido = new PedidoDistribuicao();
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_PEDIDO_DISTRIBUICAO WHERE cd_pedido_distribuicao = ?";
			PreparedStatement pstm;
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, codPedidoDistribuicao);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				pedido.setCodigo(rs.getInt("cd_pedido_distribuicao"));
				pedido.setPedidoVenda(new PedidoVendaDAO().buscarPedidoVenda(rs.getInt("cd_pedido_venda"), conn));
				pedido.setDataSolicitacao(rs.getDate("dt_solicitacao").toLocalDate());
				pedido.setDataEnvio(rs.getDate("dt_envio").toLocalDate());
				pedido.setObservacao(rs.getString("ob_observacao"));
			}
			pedido.setListaItem(new ItemDistribuicaoDAO().listarItensDistribuicao(codPedidoDistribuicao, conn));
			
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new SQLException("Erro ao executar a Query.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return pedido;
	}
	
}
