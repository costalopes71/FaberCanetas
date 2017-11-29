package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabercanetas.to.PedidoVenda;

/**
 * Classe que persiste os dados do Pedido de Venda no Banco de Dados. Faz o CRUD completo.
 * @author TDS Tecnologia
 *
 */
public class PedidoVendaDAO {

	/**
	 * Método que insere um pedido de venda no Banco de Dados.
	 * @param pedido PedidoVenda (objeto PedidoVenda a ser inserido no BD)
	 * @return codigoPedido (int), representa o codigo do pedido de venda que foi gerado pela sequence no banco. 
	 * @throws SQLException
	 */
	public int inserirPedidoVenda(PedidoVenda pedido) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			
			String sql = "INSERT INTO T_TDS_PEDIDO_VENDA VALUES(SQ_PEDIDO_VENDA_TDS.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql, new String[]{"cd_pedido_venda"});
			pstm.setInt(1, pedido.getCliente().getCodigo());
			pstm.setInt(2, pedido.getFuncionario().getCodigo());
			pstm.setDate(3, java.sql.Date.valueOf(pedido.getDataPedido()));
			pstm.setString(4, pedido.getStatusPedido());
			pstm.setInt(5, pedido.getPrazoEntrega());
			pstm.setString(6, pedido.getObsPedido());
			pstm.setDouble(7, pedido.getValorFrete());
			pstm.executeUpdate();
			
			ResultSet rs = pstm.getGeneratedKeys();
			int codigoPedido = 0;
			
			if (rs.next()) {
				codigoPedido = rs.getInt(1);
			}
			
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
	 * Método que busca um pedido de venda no Banco de Dados e retorna um objeto PedidoVenda com todos os dados
	 * @param codPedidoVenda (int : código de venda do pedido a ser buscado)
	 * @return PedidoVenda (objeto PedidoVenda)
	 * @throws SQLException
	 */
	public PedidoVenda buscarPedidoVenda(int codPedidoVenda) throws SQLException {
		
		Connection conn = null;
		PedidoVenda pedido = new PedidoVenda();
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_PEDIDO_VENDA WHERE cd_pedido_venda = ?";
			PreparedStatement pstm;
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, codPedidoVenda);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				pedido.setCodigo(rs.getInt("cd_pedido_venda"));
				pedido.setCliente(new ClienteDAO().buscarCliente(rs.getInt("cd_parceiro")));
				pedido.setFuncionario(new FuncionarioDAO().buscarFuncionario(rs.getInt("cd_funcionario")));
				pedido.setDataPedido(rs.getDate("dt_pedido").toLocalDate());
				pedido.setStatusPedido(rs.getString("st_pedido_venda"));
				pedido.setPrazoEntrega(rs.getInt("pz_entrega"));
				pedido.setObsPedido(rs.getString("ob_pedido_venda"));
				pedido.setValorFrete(rs.getDouble("vl_frete"));
			}
			pedido.setListaItem(new ItemVendaDAO().listarItensVenda(codPedidoVenda, conn));
		} catch (SQLException e) {
			throw new SQLException("Erro ao executar a Query.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return pedido;
	}
	
	/**
	 * Método que busca um pedido de venda no Banco de Dados e retorna um objeto PedidoVenda com todos os dados
	 * @param codPedidoVenda (int : código de venda do pedido a ser buscado)
	 * @param Connection conn
	 * @return PedidoVenda (objeto PedidoVenda)
	 * @throws SQLException
	 */
	public PedidoVenda buscarPedidoVenda(int codPedidoVenda, Connection conn) throws SQLException {
		
		PedidoVenda pedido = new PedidoVenda();
		
		try {
			String query = "SELECT * FROM T_TDS_PEDIDO_VENDA WHERE cd_pedido_venda = ?";
			PreparedStatement pstm;
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, codPedidoVenda);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				pedido.setCodigo(rs.getInt("cd_pedido_venda"));
				pedido.setCliente(new ClienteDAO().buscarCliente(rs.getInt("cd_parceiro")));
				pedido.setFuncionario(new FuncionarioDAO().buscarFuncionario(rs.getInt("cd_funcionario")));
				pedido.setDataPedido(rs.getDate("dt_pedido").toLocalDate());
				pedido.setStatusPedido(rs.getString("st_pedido_venda"));
				pedido.setPrazoEntrega(rs.getInt("pz_entrega"));
				pedido.setObsPedido(rs.getString("ob_pedido_venda"));
				pedido.setValorFrete(rs.getDouble("vl_frete"));
			}
			pedido.setListaItem(new ItemVendaDAO().listarItensVenda(codPedidoVenda, conn));
		} catch (SQLException e) {
			throw new SQLException("Erro ao executar a Query.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return pedido;
	}
	
	/**
	 * Método que retorna um ArrayList com todos os pedidos de venda de um cliente.
	 * @param codigoCliente (codigo do cliente a ser pesquisado os Pedidos)
	 * @return ArrayList<PedidoVenda> (retorna uma coleção com os pedidos)
	 * @throws SQLException
	 */
	public ArrayList<PedidoVenda> listarPedidosCliente(int codigoCliente) throws SQLException {
		
		Connection conn = null;
		ArrayList<PedidoVenda> listaPedido = new ArrayList<>();
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_PEDIDO_VENDA WHERE cd_parceiro = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoCliente);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				PedidoVenda pedido = new PedidoVenda();
				listaPedido.add(setPedidoVendaFromBD(pedido, rs));
			}
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return listaPedido;
	}
	
	/**
	 * Método que atualiza um pedido de venda no Banco de Dados.
	 * @param pedido (objeto PedidoVenda com as novas informacoes)
	 * @throws SQLException
	 */
	public void atualizarPedidoVenda(PedidoVenda pedido) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String update = "UPDATE T_TDS_PEDIDO_VENDA SET cd_parceiro = ?, cd_funcionario = ?, dt_pedido = ?, st_pedido_venda = ?, "
					+ "pz_entrega = ?, ob_pedido_venda = ?, vl_frete = ? WHERE cd_pedido_venda = ?";
			PreparedStatement pstm = conn.prepareStatement(update);
			pstm.setInt(1, pedido.getCliente().getCodigo());
			pstm.setInt(2, pedido.getFuncionario().getCodigo());
			pstm.setDate(3, java.sql.Date.valueOf(pedido.getDataPedido()));
			pstm.setString(4, pedido.getStatusPedido());
			pstm.setInt(5, pedido.getPrazoEntrega());
			pstm.setString(6, pedido.getObsPedido());
			pstm.setDouble(7, pedido.getValorFrete());
			pstm.setInt(8, pedido.getCodigo());
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
	
	/**
	 * Método que deleta um pedido de venda do Banco de Dados.
	 * @param codPedidoVenda (codigo do pedido de venda a ser deletado do BD)
	 * @throws SQLException
	 */
	public void deletarPedidoVenda(int codPedidoVenda) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_TDS_PEDIDO_VENDA WHERE cd_pedido_venda = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, codPedidoVenda);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o banco de dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public List<PedidoVenda> listarPedidosEmAberto() throws SQLException {
		
		Connection conn = null;
		List<PedidoVenda> listaPedidoVenda = null;
		PedidoVenda pedido = null;
		
		try {
			listaPedidoVenda = new ArrayList<>();
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_PEDIDO_VENDA WHERE st_pedido_venda = 'P' OR st_pedido_venda = 'A'";
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				pedido = new PedidoVenda();
				listaPedidoVenda.add(setPedidoVendaFromBD(pedido, rs));
			}
			
			return listaPedidoVenda;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que atualiza o status de um pedido de venda no Banco de Dados.
	 * @param pedido (objeto PedidoVenda com as novas informacoes)
	 * @throws SQLException
	 */
	public void atualizarStatusPedidoVenda(PedidoVenda pedido) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String update = "UPDATE T_TDS_PEDIDO_VENDA SET st_pedido_venda = 'A' WHERE cd_pedido_venda = ?";
			PreparedStatement pstm = conn.prepareStatement(update);
			pstm.setInt(1, pedido.getCodigo());
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
	
	public void atualizarStatusPedidoVenda2(PedidoVenda pedido) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String update = "UPDATE T_TDS_PEDIDO_VENDA SET st_pedido_venda = ? WHERE cd_pedido_venda = ?";
			PreparedStatement pstm = conn.prepareStatement(update);
			pstm.setString(1, pedido.getStatusPedido());
			pstm.setInt(2, pedido.getCodigo());
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
	
	private PedidoVenda setPedidoVendaFromBD(PedidoVenda pedido, ResultSet rs) throws SQLException {
		pedido.setCodigo(rs.getInt("cd_pedido_venda"));
		pedido.setCliente(new ClienteDAO().buscarCliente(rs.getInt("cd_parceiro")));
		pedido.setFuncionario(new FuncionarioDAO().buscarFuncionario(rs.getInt("cd_funcionario")));
		pedido.setDataPedido(rs.getDate("dt_pedido").toLocalDate());
		pedido.setStatusPedido(rs.getString("st_pedido_venda"));
		pedido.setPrazoEntrega(rs.getInt("pz_entrega"));
		pedido.setObsPedido(rs.getString("ob_pedido_venda"));
		pedido.setValorFrete(rs.getDouble("vl_frete"));
		return pedido;
	}
	
}
