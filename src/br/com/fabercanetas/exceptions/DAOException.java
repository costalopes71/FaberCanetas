package br.com.fabercanetas.exceptions;

/**
 * Classe de exceção da camada de persistência..
 * @author TDS Tecnologia
 *
 */
public class DAOException extends Exception {
	
	private static final long serialVersionUID = 6842706614494239992L;

	public DAOException(String msg) {
		super(msg);
	}
	
}
