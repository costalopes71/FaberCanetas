package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabercanetas.to.Produto;

/**
 * Classe no padrão DAO da entidade <code>T_SYS_PRODUTO</code> no banco de dados
 * @author TDS Tecnologia
 * @see br.com.fabercanetas.to.Produto
 */
public class ProdutoDAO {


	/**
	 * Método para gravar um produto no banco de dados.
	 * @param objeto produto a ser gravado no banco (Produto)
	 * @throws SQLException
	 */
	public void inserirProduto(Produto produto) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_TDS_MP_PRODUTO VALUES (SQ_MP_PRODUTO.NEXTVAL, ?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql, new String[]{"cd_produto"});
			pstm.setString(1, produto.getDescricaoProduto());
			pstm.setString(2, produto.getUnidadeMedida());
			pstm.setDouble(3, produto.getValorUnitario());
			pstm.executeUpdate();
			
			ResultSet rs = pstm.getGeneratedKeys();
			int codigoProduto = 0;
			
			if (rs.next()) {
				codigoProduto = rs.getInt(1);
			}
			
			String sql2 = "INSERT INTO T_TDS_PRODUTO VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement pstm2 = conn.prepareStatement(sql2);
			pstm2.setInt(1, codigoProduto);
			pstm2.setString(2, produto.getNomeProduto());
			pstm2.setString(3, produto.getUrlImgProduto());
			pstm2.setString(4, produto.getDescricaoProdutoCompleta());
			pstm2.setString(5, produto.getDescricaoSimples());
			pstm2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que busca um determinado produto pelo seu id e retorna o objeto desse produto.
	 * @param codigoProduto codigo do produto que se quer buscar.
	 * @return Produto objeto produto buscado.
	 * @throws SQLException
	 */
	public Produto buscarProduto(int codigoProduto) throws SQLException {
		
		Connection conn = null;
		Produto produto = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_MP_PRODUTO MP JOIN T_TDS_PRODUTO P ON P.cd_produto = MP.cd_produto WHERE P.cd_produto = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoProduto);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				produto = ProdutoDAO.setProdutoFromBD(produto, rs);
			}
			return produto;
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
	 * Busca produto por texto
	 * @param texto
	 * @return Produto
	 * @throws SQLException
	 */
	public Produto buscarProduto(String texto) throws SQLException {
		
		Connection conn = null;
		Produto produto = null;
		
		texto = String.format("%%%s%%", texto);
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT * FROM T_TDS_PRODUTO P JOIN T_TDS_MP_PRODUTO MP ON P.cd_produto = MP.cd_produto WHERE P.nm_produto like ? OR P.ds_produto1 like ? or P.ds_produto_simples like ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, texto);
			pstm.setString(2, texto);
			pstm.setString(3, texto);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				produto = ProdutoDAO.setProdutoFromBD(produto, rs);
			}
			return produto;
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
	 * Método que atualiza as informações de um determinado produto.
	 * @param produto Objeto produto que será atualizado
	 * @throws SQLException
	 */
	public void atualizarProduto(Produto produto) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String update = "UPDATE T_TDS_PRODUTO SET nm_produto = ?, tx_urlimg = ?, ds_produto1 = ?, ds_produto_simples = ? WHERE cd_produto = ?";
			PreparedStatement pstm = conn.prepareStatement(update);
			pstm.setString(1, produto.getNomeProduto());
			pstm.setString(2, produto.getUrlImgProduto());
			pstm.setString(3, produto.getDescricaoProdutoCompleta());
			pstm.setString(4, produto.getDescricaoSimples());
			pstm.setInt(5, produto.getCodigo());
			pstm.executeUpdate();
			
			String update2 = "UPDATE T_TDS_MP_PRODUTO SET ds_produto = ?, ds_unidade_medida = ?, vl_unitario = ? WHERE cd_produto = ?";
			PreparedStatement pstm2 = conn.prepareStatement(update2);
			pstm2.setString(1, produto.getDescricaoProduto());
			pstm2.setString(2, produto.getUnidadeMedida());
			pstm2.setDouble(3, produto.getValorUnitario());
			pstm2.setInt(4, produto.getCodigo());
			pstm2.executeUpdate();
			
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
	 * Método que exclui um produto. Espera como parâmetro o inteiro id do produto a ser excluído.
	 * @param id_produto Id do produto que será deletado
	 * @throws SQLException
	 */
	public void deletarProduto(int codigoProduto) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_TDS_PRODUTO WHERE cd_produto = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, codigoProduto);
			pstm.executeUpdate();
			
			String sql2 = "DELETE FROM T_TDS_MP_PRODUTO WHERE cd_produto = ?";
			PreparedStatement pstm2 = conn.prepareStatement(sql2);
			pstm2.setInt(1, codigoProduto);
			pstm2.executeUpdate();
			
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
	 * Método que cria uma lista com o intervalo de produtos que deseja. Por exemplo: codigoInicio 3 codigoFim 6 a lista criada
	 * começara no produto de codigo 3 e irá conter até o produto de codigo 6. 
	 * @param codigoInicio id do primeiro produto que deseja adicionar a lista
	 * @param codigoFim id do ultimo produto que deseja adicionar a lista
	 * @return Lista<Produto> retorna uma coleção de Produtos
	 * @throws SQLException
	 */
	public List<Produto> listaProduto(int codigoInicio, int codigoFim) throws SQLException {
		
		Connection conn = null;
		String query = "SELECT * FROM T_TDS_MP_PRODUTO MP JOIN T_TDS_PRODUTO P ON P.cd_produto = MP.cd_produto WHERE P.cd_produto BETWEEN ? AND ?";
		List<Produto> listaProduto = new ArrayList<>();
		Produto produto = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setInt(1, codigoInicio);
			pstm.setInt(2, codigoFim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				produto = ProdutoDAO.setProdutoFromBD(produto, rs);
				listaProduto.add(produto);
			}
			return listaProduto;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao criar a conexão ou fazer query no Banco de Dados.", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Método que seta um objeto Produto com as informacoes persistidas no BD e retorna este objeto.
	 * @param produto
	 * @param rs, ResultSet
	 * @return produto, objeto Produto
	 * @throws SQLException
	 */
	private static Produto setProdutoFromBD(Produto produto, ResultSet rs) throws SQLException {
		produto = new Produto();
		produto.setCodigo(rs.getInt("cd_produto"));
		produto.setDescricaoProduto(rs.getString("ds_produto"));
		produto.setUnidadeMedida(rs.getString("ds_unidade_medida"));
		produto.setValorUnitario(rs.getDouble("vl_unitario"));
		produto.setNomeProduto(rs.getString("nm_produto"));
		produto.setUrlImgProduto(rs.getString("tx_urlimg"));
		produto.setDescricaoProdutoCompleta(rs.getString("ds_produto1"));
		produto.setDescricaoSimples(rs.getString("ds_produto_simples"));
		return produto;
	}
	
}
