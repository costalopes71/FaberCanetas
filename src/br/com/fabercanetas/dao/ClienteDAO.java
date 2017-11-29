package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fabercanetas.to.Cliente;

/**
 * Classe que acessa o banco de dados e retorna dados do cliente ou faz querys. CRUD completo do beans Cliente.
 * @author TDS Tecnologia
 * @version 1.1
 */
public class ClienteDAO {

	/**
	 * Método que inseri um novo cliente no Banco de Dados. Espera por parametro o Objeto Cliente.
	 * @param Cliente (cliente a ser inserido)
	 * @throws SQLException
	 */
	public void inserirCliente(Cliente cliente) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_TDS_PARCEIRO VALUES(SQ_PARCEIRO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, cliente.getRazaoSocial());
			pstm.setString(2, cliente.getNomeFantasia());
			pstm.setLong(3, cliente.getCnpj());
			pstm.setLong(4, cliente.getInscricaoEstadual());
			pstm.setLong(5, cliente.getInscricaoMunicipal());
			pstm.setString(6, cliente.getEmail());
			pstm.setDate(7, java.sql.Date.valueOf(cliente.getDataCadastro()));
			pstm.setString(8, cliente.getSenha());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar com o BD ou manipular os dados do Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
	}
	
	/**
	 * Método que pesquisa um cliente no banco e retorna os dados do cliente através de um objeto Cliente.
	 * O parâmetro que deve ser passado para a pesquisa é o id do cliente.
	 * @param codigoCliente (int)
	 * @return Cliente (retorna um objeto Cliente com os dados do cliente pesquisado)
	 * @throws SQLException
	 */
	public Cliente buscarCliente(int codigoCliente) throws SQLException {
		
		Connection conn = null;
		Cliente cliente = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_PARCEIRO WHERE cd_parceiro = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoCliente);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				cliente = ClienteDAO.setClienteFromBD(cliente, rs);
			}
			return cliente;
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
	 * Método que pesquisa um cliente no banco e retorna os dados do cliente através de um objeto Cliente.
	 * O parâmetro que deve ser passado para a pesquisa é o e-mail do cliente e a senha do cliente.
	 * @param email (e-mail do Cliente), senha (senha do cliente)
	 * @return Cliente (retorna um objeto Cliente)
	 * @throws SQLException
	 */
	public Cliente buscarCliente(String email, String senha) throws SQLException {
		
		Connection conn = null;
		Cliente cliente = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_PARCEIRO WHERE ds_email = ? AND tx_senha = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, email);
			pstm.setString(2, senha);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				cliente = ClienteDAO.setClienteFromBD(cliente, rs);
			}
			return cliente;
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
	 * Método que pesquisa um cliente no banco e retorna os dados do cliente através de um objeto Cliente.
	 * O parâmetro que deve ser passado para a pesquisa é o e-mail do cliente.
	 * @param email (e-mail do Cliente)
	 * @return Cliente (retorna um objeto Cliente)
	 * @throws SQLException
	 */
	public Cliente buscarCliente(String email) throws SQLException {
		
		Connection conn = null;
		Cliente cliente = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_PARCEIRO WHERE ds_email = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, email);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				cliente = ClienteDAO.setClienteFromBD(cliente, rs);
			}
			return cliente;
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
	 * Método que atualiza as informações de um determinado cliente.
	 * @param Cliente (objeto Cliente a ser atualizado)
	 * @throws SQLException
	 */
	public void atualizarCliente(Cliente cliente) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_TDS_PARCEIRO SET nm_social = ?, nm_fantasia = ?, nr_cnpj = ?, nr_inscricao_estadual = ?, "
					+ " nr_inscricao_municipal = ?,  ds_email = ?, dt_cadastro = ?, tx_senha = ? WHERE cd_parceiro = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, cliente.getRazaoSocial());
			pstm.setString(2, cliente.getNomeFantasia());
			pstm.setLong(3, cliente.getCnpj());
			pstm.setLong(4, cliente.getInscricaoEstadual());
			pstm.setLong(5, cliente.getInscricaoMunicipal());
			pstm.setString(6, cliente.getEmail());
			pstm.setDate(7, java.sql.Date.valueOf(cliente.getDataCadastro()));
			pstm.setString(8, cliente.getSenha());
			pstm.setInt(9, cliente.getCodigo());
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que exclui um cliente. Espera como parâmetro o inteiro id do cliente a ser excluído.
	 * @param int idCliente
	 * @throws SQLException
	 */
	public void deletarCliente(int codigoCliente) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_TDS_PARCEIRO WHERE cd_parceiro = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, codigoCliente);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados.", e);
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método valida o login do usuário. Acessa o banco de dados e retorna um booleano, true se o login
	 * existir e o e-mail e senha forem válidos e false se o login não existir ou um dos dois parametros
	 * estiverem errados.
	 * @param email String, e-mail do usuario (login)
	 * @param senha	String, senha do usuario
	 * @return boolean (true se o login for valido, false se for invalido)
	 * @throws SQLException
	 */
//	public Cliente validaLogin(String email, String senha) throws SQLException {
//		
//		Connection conn = null;
//		
//		try {
//			conn = ConnectionManager.getInstance().getConnection();
//			String query = "SELECT * FROM T_TDS_PARCEIRO WHERE ds_email = ? AND tx_senha = ?";
//			PreparedStatement pstm = conn.prepareStatement(query);
//			pstm.setString(1, email);
//			pstm.setString(2, senha);
//			ResultSet rs = pstm.executeQuery();
//			if (rs.next()) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (SQLException e) {
//			throw new SQLException("Erro ao conectar ou manipular o banco", e);
//		} finally {
//			if(conn != null) {
//				conn.close();
//			}
//		}
//	}
	
	/**
	 * Metodo que retorna um objeto Cliente com todas as informacos do cliente que foi pesquisado no banco de dados e que esta dentro
	 * do ResultSet.
	 * @param <code>Cliente</code> cliente, um objeto do tipo Cliente
	 * @param <code>ResultSet</code> resultset, um objeto do tipo ResultSet
	 * @return <code>Cliente</code> cliente, um objeto do tipo Cliente
	 * @throws SQLException
	 */
	private static Cliente setClienteFromBD(Cliente cliente, ResultSet rs) throws SQLException {
			cliente = new Cliente();
			cliente.setCodigo(rs.getInt("cd_parceiro"));
			cliente.setRazaoSocial(rs.getString("nm_social"));
			cliente.setNomeFantasia(rs.getString("nm_fantasia"));
			cliente.setCnpj(rs.getLong("nr_cnpj"));
			cliente.setInscricaoEstadual(rs.getLong("nr_inscricao_estadual"));
			cliente.setInscricaoMunicipal(rs.getLong("nr_inscricao_municipal"));
			cliente.setEmail(rs.getString("ds_email"));
			cliente.setDataCadastro(rs.getDate("dt_cadastro").toLocalDate());
			cliente.setSenha(rs.getString("tx_senha"));
		return cliente;
	}
	
}
