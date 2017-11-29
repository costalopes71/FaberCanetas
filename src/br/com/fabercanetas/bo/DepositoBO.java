package br.com.fabercanetas.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fabercanetas.dao.DepositoDAO;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.to.Deposito;
import br.com.fabercanetas.to.ItemEstoque;

public class DepositoBO {
	
	private DepositoDAO depositoDao;
	
	public DepositoBO() {
		depositoDao = new DepositoDAO();
	}
	
	public Deposito buscarDeposito(int codigoDeposito) throws RetrieveException {
		try {
			return depositoDao.buscarDeposito(codigoDeposito);
		} catch (SQLException e){
			throw new RetrieveException();
		}
	}
	
	public ArrayList<ItemEstoque> getItems(int codigoDeposito) throws RetrieveException {
		try {
			return depositoDao.getItems(codigoDeposito);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}

}