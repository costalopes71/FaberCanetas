package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fabercanetas.to.Funcao;

/**
 * Classe de persistência de dados de função no banco de dados.
 * @author TDS Tecnologia
 * @see br.com.fabercanetas.bo.FuncaoBO
 * @see br.com.fabercanetas.to.Funcao
 * @since 01/10/2017
 * @version 1.0
 *
 */
public class FuncaoDAO {

	/**
	 * Método que busca um registro de função no banco de dados através de um número inteiro que representa o código da função.
	 * O método retorna um objeto do tipo Funcao com todos os dados existentes no banco de dados daquela função.
	 * @param int codigoFuncao, representa o código da função a buscar.
	 * @return Funcao funcao. Objeto funcao do tipo Funcao criado com os dados encontrados no BD.
	 * @throws SQLException
	 */
	public Funcao buscarFuncao(int codigoFuncao) throws SQLException {
		
		Connection conn = null;
		Funcao funcao = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_FUNCAO WHERE cd_funcao = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoFuncao);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				funcao = new Funcao();
				funcao.setCodigo(rs.getInt("cd_funcao"));
				funcao.setNome(rs.getString("ds_funcao"));
			}
			return funcao;
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
