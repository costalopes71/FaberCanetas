package br.com.fabercanetas.exceptions;

@SuppressWarnings("serial")
/**
 * Classe de exceção quando da tentativa de deletar algum dado do Banco de Dados.
 * @author TDS Tecnologia
 *
 */
public class DeleteException extends DAOException {

	public DeleteException() {
		super("ERRO - Não foi possível deletar. Um erro aconteceu ao manipular ou conectar com o banco de dados.");
	}
	
}
