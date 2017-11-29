package br.com.fabercanetas.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fabercanetas.dao.RelatorioDAO;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.to.TicketMedio;

public class RelatorioBO {
	
	public String getVendaPorCD() throws RetrieveException {
		try {
			return new RelatorioDAO().getVendaPorCD();
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}

	public ArrayList<TicketMedio> getTicketMedio() throws RetrieveException {
		try {
			return new RelatorioDAO().getTicketMedio();
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	public String getRankingCliente() throws RetrieveException {
		try {
			return new RelatorioDAO().getRankingCliente();
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	public String getRankingProduto() throws RetrieveException {
		try {
			return new RelatorioDAO().getRankingProduto();
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	public String getFaturamentoRegiao() throws RetrieveException {
		try {
			return new RelatorioDAO().getFaturamentoRegiao();
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	public String getFaturamentoMensal() throws RetrieveException {
		try {
			return new RelatorioDAO().getFaturamentoMensal();
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	public String getEstoque(int codigoEstoque) throws RetrieveException {
		try {
			return new RelatorioDAO().getEstoque(codigoEstoque);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
}
