package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fabercanetas.to.SolicitacaoCompra;

public class SolicitacaoCompraDAO {

	/**
	 * Método que pesquisa uma solicitacao de compra no banco e retorna os dados da solicitacao através de um objeto SolicitacaoCompra.
	 * O parâmetro que deve ser passado para a pesquisa é o codigo da solicitacao.
	 * @param codigoSolicitacao (int)
	 * @return SolicitacaoCompra (retorna um objeto SolicitacaoCompra com os dados da solicitacao pesquisada)
	 * @throws SQLException
	 */
	public SolicitacaoCompra buscarSolicitacaoCompra(int codigoSolicitacao) throws SQLException {
		
		Connection conn = null;
		SolicitacaoCompra sc = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_SOLIC_COMPRA WHERE cd_solicitacao_compra = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoSolicitacao);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				sc = SolicitacaoCompraDAO.setSolicitacaoFromBD(sc, rs);
			}
			return sc;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o Bando de Dados.", e);
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Metodo que retorna um objeto SolicitacaoCompra com todas as informacos da solicitacao que foi pesquisado no banco de dados e que esta dentro
	 * do ResultSet.
	 * @param <code>SolicitacaoCompra</code> solicitacao, um objeto do tipo SolicitacaoCompra
	 * @param <code>ResultSet</code> resultset, um objeto do tipo ResultSet
	 * @return <code>SolicitacaoCompra</code> solicitacao, um objeto do tipo SolicitacaoCompra
	 * @throws SQLException
	 */
	private static SolicitacaoCompra setSolicitacaoFromBD(SolicitacaoCompra sc, ResultSet rs) throws SQLException {
			sc = new SolicitacaoCompra();
			sc.setCodigo(rs.getInt("cd_solicitacao_compra"));
			sc.setDataSolicitacao(rs.getDate("dt_solicitacao").toLocalDate());
			sc.setObservacao(rs.getString("ob_solicitacao_compra"));
			sc.setPrazoEntrega(rs.getInt("pz_entrega"));
			sc.setFuncionario(new FuncionarioDAO().buscarFuncionario(rs.getInt("cd_funcionario")));
		return sc;
	}

	
}
