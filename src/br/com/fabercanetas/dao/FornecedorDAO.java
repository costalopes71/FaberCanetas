package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fabercanetas.to.Fornecedor;

public class FornecedorDAO {

	/**
	 * Método que pesquisa um fornecedor no banco e retorna os dados do fornecedor através de um objeto Fornecedor.
	 * O parâmetro que deve ser passado para a pesquisa é o codigo do fornecedor.
	 * @param codigoFornecedor (int)
	 * @return Fornecedor (retorna um objeto Fornecedor com os dados do fornecedor pesquisado)
	 * @throws SQLException
	 */
	public Fornecedor buscarFornecedor(int codigoFornecedor) throws SQLException {
		
		Connection conn = null;
		Fornecedor fornecedor = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_PARCEIRO WHERE cd_parceiro = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoFornecedor);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				fornecedor = FornecedorDAO.setFornecedorFromBD(fornecedor, rs);
			}
			return fornecedor;
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
	 * Metodo que retorna um objeto Fornecedor com todas as informacos do fornecedor que foi pesquisado no banco de dados e que esta dentro
	 * do ResultSet.
	 * @param <code>Fornecedor</code> fornecedor, um objeto do tipo Fornecedor
	 * @param <code>ResultSet</code> resultset, um objeto do tipo ResultSet
	 * @return <code>Fornecedor</code> fornecedor, um objeto do tipo Fornecedor
	 * @throws SQLException
	 */
	private static Fornecedor setFornecedorFromBD(Fornecedor fornecedor, ResultSet rs) throws SQLException {
			fornecedor = new Fornecedor();
			fornecedor.setCodigo(rs.getInt("cd_parceiro"));
			fornecedor.setRazaoSocial(rs.getString("nm_social"));
			fornecedor.setNomeFantasia(rs.getString("nm_fantasia"));
			fornecedor.setCnpj(rs.getLong("nr_cnpj"));
			fornecedor.setInscricaoEstadual(rs.getLong("nr_inscricao_estadual"));
			fornecedor.setInscricaoMunicipal(rs.getLong("nr_inscricao_municipal"));
			fornecedor.setEmail(rs.getString("ds_email"));
			fornecedor.setDataCadastro(rs.getDate("dt_cadastro").toLocalDate());
			fornecedor.setSenha(rs.getString("tx_senha"));
		return fornecedor;
	}
	
}
