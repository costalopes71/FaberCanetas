package br.com.fabercanetas.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fabercanetas.dao.ItemDistribuicaoDAO;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.to.ItemDistribuicao;

public class ItemDistribuicaoBO {

	public void inserirItem(ItemDistribuicao item, int codigoDeposito) throws RetrieveException {
		try {
			new ItemDistribuicaoDAO().inserirItem(item, codigoDeposito);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	public List<ItemDistribuicao> listarItensDistribuicao(int codigoDistribuicao) throws RetrieveException {
		try {
			return new ItemDistribuicaoDAO().listarItensDistribuicao(codigoDistribuicao);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	public List<ItemDistribuicao> listarItensDistribuicao(int codigoDistribuicao, Connection conn) throws RetrieveException {
		try {
			return new ItemDistribuicaoDAO().listarItensDistribuicao(codigoDistribuicao, conn);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
}
