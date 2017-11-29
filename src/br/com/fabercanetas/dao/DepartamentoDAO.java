package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fabercanetas.to.Departamento;

/**
 * Classe de persistência de dados de departamento no banco de dados.
 * @author TDS Tecnologia
 * @see br.com.fabercanetas.bo.DepartamentoBO
 * @see br.com.fabercanetas.to.Departamento
 * @since 01/10/2017
 * @version 1.0
 *
 */
public class DepartamentoDAO {

	/**
	 * Método que busca um registro de departamento no banco de dados através de um número inteiro que representa o código do departamento.
	 * O método retorna um objeto do tipo Departamento com todos os dados existentes no banco de dados daquele departamento.
	 * @param int codigoDepartamento, representa o código do departamento a buscar.
	 * @return Departamento departamento. Objeto departamento do tipo Departamento criado com os dados encontrados no BD.
	 * @throws SQLException
	 */
	public Departamento buscarDepartamento(int codigoDepartamento) throws SQLException {
		
		Connection conn = null;
		Departamento departamento = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_DEPARTAMENTO WHERE cd_departamento = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoDepartamento);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				departamento = new Departamento();
				departamento.setCodigo(rs.getInt("cd_departamento"));
				departamento.setNome(rs.getString("nm_departamento"));
			}
			return departamento;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o Bando de Dados.", e);
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
	}
	
}
