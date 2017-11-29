package br.com.fabercanetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import br.com.fabercanetas.to.TicketMedio;

public class RelatorioDAO {

	public String getVendaPorCD() throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			String query = "SELECT sum(ID.QT_PRODUTO) as total , D.NM_DEPOSITO FROM t_tds_item_distribuicao ID join "
					+ "t_tds_pedido_distribuicao PD on ID.cd_pedido_distribuicao = PD.cd_pedido_distribuicao join T_TDS_ESTOQUE_PRODUTO"
					+ " EP on EP.CD_ITEM_ESTOQUE = ID.CD_ITEM_ESTOQUE JOIN T_TDS_ESTOQUE E on E.CD_ITEM_ESTOQUE = EP.CD_ITEM_ESTOQUE "
					+ "JOIN T_TDS_DEPOSITO D ON D.CD_DEPOSITO = E.CD_DEPOSITO GROUP BY D.NM_DEPOSITO ORDER BY  TOTAL DESC";
			PreparedStatement pstm = conn.prepareStatement(query);
			
			ResultSet rs = pstm.executeQuery(); 
			
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			while(rs.next()) {
				sb.append(String.format("{\"name\":\"%s\", \"qtd\":\"%s\"}", rs.getString("nm_deposito"), rs.getInt("total")));
				sb.append(",");
			}
			String resultado = sb.substring(0, sb.length() - 1) + "]";
			return resultado;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public ArrayList<TicketMedio> getTicketMedio() throws SQLException {
		
		Connection conn = null;
		ArrayList<TicketMedio> listaTicket = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			listaTicket = new ArrayList<>();
			String query = "SELECT ROUND(VALOR/QTDE,2) FROM (SELECT  SUM(VL_PRODUTO*QT_PRODUTO) AS VALOR FROM T_TDS_ITEM_DISTRIBUICAO ID where "
					+ "ID.CD_ITEM_ESTOQUE between ? and ?), (SELECT COUNT(PV.CD_PEDIDO_DISTRIBUICAO) AS QTDE FROM "
					+ "T_TDS_PEDIDO_DISTRIBUICAO PV)";
			PreparedStatement pstm = conn.prepareStatement(query);
			PreparedStatement pstm2 = conn.prepareStatement(query);
			PreparedStatement pstm3 = conn.prepareStatement(query);
			pstm.setInt(1, 4);
			pstm.setInt(2, 6);
			pstm2.setInt(1, 7);
			pstm2.setInt(2, 9);
			pstm3.setInt(1, 10);
			pstm3.setInt(2, 12);
			ResultSet rs = pstm.executeQuery();
			ResultSet rs2 = pstm2.executeQuery();
			ResultSet rs3 = pstm3.executeQuery();
			
			TicketMedio aux = null;
			DecimalFormat df = new DecimalFormat("0.####");
			
			while(rs.next()) {
				aux = new TicketMedio();
				aux.setValor(df.format(rs.getDouble(1)));
				aux.setLocal("TICKET MÉDIO CENTRAL");
				aux.setCorBox("aqua");
				listaTicket.add(aux);
			}
			
			while(rs2.next()) {
				aux = new TicketMedio();
				aux.setValor(df.format(rs2.getDouble(1)));
				aux.setLocal("TICKET MÉDIO SUL");
				aux.setCorBox("red");
				listaTicket.add(aux);
			}
			
			while(rs3.next()) {
				aux = new TicketMedio();
				aux.setValor(df.format(rs3.getDouble(1)));
				aux.setLocal("TICKET MÉDIO NORDESTE");
				aux.setCorBox("orange");
				listaTicket.add(aux);
			}
			
			rs.close();
			rs2.close();
			rs3.close();
			pstm.close();
			pstm2.close();
			pstm3.close();
			
			return listaTicket;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public String getRankingCliente() throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			StringBuilder sb = new StringBuilder();
			String query = "Select * from (SELECT  P.NM_FANTASIA AS CLIENTE,ROUND(SUM(IV.QT_PRODUTO*IV.VL_PRODUTO),2) AS VALOR "
					+ "FROM T_TDS_ITEM_VENDA IV JOIN T_TDS_PEDIDO_VENDA PV ON IV.CD_PEDIDO_VENDA = PV.CD_PEDIDO_VENDA JOIN T_TDS_PARCEIRO "
					+ "P ON PV.CD_PARCEIRO = P.CD_PARCEIRO GROUP BY P.NM_FANTASIA ORDER BY VALOR DESC) WHERE ROWNUM < 6"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			sb.append("[");
			while (rs.next()) {
				sb.append("{\"name\": \"").append(rs.getString("cliente")).append("\", \"y\": ").append(rs.getDouble("valor")).append("},");
			}
			
			String resultado = sb.substring(0, sb.length() - 1) + "]"; 
			return resultado;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public String getRankingProduto() throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			StringBuilder sb = new StringBuilder();
			String query = "SELECT sum(ID.QT_PRODUTO) as total , MP.DS_PRODUTO FROM t_tds_item_distribuicao ID join t_tds_pedido_distribuicao "
					+ "PD on ID.cd_pedido_distribuicao = PD.cd_pedido_distribuicao join T_TDS_ESTOQUE_PRODUTO EP on EP.CD_ITEM_ESTOQUE "
					+ "= ID.CD_ITEM_ESTOQUE JOIN T_TDS_MP_PRODUTO MP ON MP.CD_PRODUTO = EP.CD_PRODUTO GROUP BY MP.DS_PRODUTO ORDER BY "
					+ "TOTAL DESC"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			sb.append("[");
			while (rs.next()) {
				sb.append("{\"name\": \"").append(rs.getString("ds_produto")).append("\", \"y\": ").append(rs.getDouble("total")).append("},");
			}
			
			String resultado = sb.substring(0, sb.length() - 1) + "]"; 
			return resultado;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public String getFaturamentoRegiao() throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			StringBuilder sb = new StringBuilder();
			String query = "SELECT ROUND(sum(ID.QT_PRODUTO*ID.VL_PRODUTO),2) as total , D.NM_DEPOSITO FROM t_tds_item_distribuicao ID "
					+ "join t_tds_pedido_distribuicao PD on ID.cd_pedido_distribuicao = PD.cd_pedido_distribuicao join T_TDS_ESTOQUE_PRODUTO"
					+ " EP on EP.CD_ITEM_ESTOQUE = ID.CD_ITEM_ESTOQUE JOIN T_TDS_ESTOQUE E on E.CD_ITEM_ESTOQUE = EP.CD_ITEM_ESTOQUE JOIN "
					+ "T_TDS_DEPOSITO D ON D.CD_DEPOSITO = E.CD_DEPOSITO GROUP BY D.NM_DEPOSITO ORDER BY  TOTAL DESC"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			sb.append("[");
			while (rs.next()) {
				sb.append("{\"name\": \"").append(rs.getString("nm_deposito")).append("\", \"y\": ").append(rs.getDouble("total")).append("},");
			}
			
			String resultado = sb.substring(0, sb.length() - 1) + "]"; 
			return resultado;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public String getFaturamentoMensal() throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			StringBuilder sb = new StringBuilder();
			String query = "SELECT TO_CHAR(PD.DT_SOLICITACAO,'MM') AS MES, ROUND(SUM(ID.VL_PRODUTO*ID.QT_PRODUTO),2) AS TOTAL FROM  "
					+ "T_TDS_ITEM_DISTRIBUICAO ID JOIN T_TDS_PEDIDO_DISTRIBUICAO PD ON ID.CD_PEDIDO_DISTRIBUICAO = PD.CD_PEDIDO_DISTRIBUICAO "
					+ "GROUP BY TO_CHAR(PD.DT_SOLICITACAO,'MM') ORDER BY MES"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			sb.append("[");
			while (rs.next()) {
				sb.append(rs.getDouble("total")).append(",");
			}
			
			String resultado = sb.substring(0, sb.length() - 1) + "]"; 
			return resultado;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public String getEstoque(int codigoEstoque) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = ConnectionManager.getInstance().getConnection();
			StringBuilder sb = new StringBuilder();
			String query = "SELECT E.CD_ITEM_ESTOQUE, E.QT_PRODUTO, E.QT_MINIMA, MPP.DS_PRODUTO FROM T_TDS_ESTOQUE E JOIN T_TDS_ESTOQUE_PRODUTO P ON E.CD_ITEM_ESTOQUE = P.CD_ITEM_ESTOQUE JOIN T_TDS_MP_PRODUTO "
					+ "MPP ON MPP.CD_PRODUTO = P.CD_PRODUTO JOIN T_TDS_DEPOSITO D ON D.CD_DEPOSITO = E.CD_DEPOSITO where D.CD_DEPOSITO = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, codigoEstoque);
			ResultSet rs = ps.executeQuery();
			
			sb.append("{\"qtd\": [");
			while (rs.next()) {
				sb.append(rs.getInt("qt_produto")).append(",");
			}
			
			String resultado = sb.substring(0, sb.length() - 1) + "], "; 
			
			PreparedStatement ps2 = conn.prepareStatement(query);
			ps2.setInt(1, codigoEstoque);
			ResultSet rs2 = ps.executeQuery();
			
			StringBuilder sb2 = new StringBuilder();
			sb2.append("\"minimo\": [");
			while (rs2.next()) {
				sb2.append(rs2.getInt("qt_minima")).append(",");
			}
			
			resultado += sb2.substring(0, sb2.length() - 1) + "]}"; 
			
			return resultado;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao conectar ou manipular o Banco de Dados", e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
		
}
