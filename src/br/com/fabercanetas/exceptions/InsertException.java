package br.com.fabercanetas.exceptions;

@SuppressWarnings("serial")
/**
 * Classe de exceção quando da tentativa de inserir algum dado do Banco de Dados.
 * @author TDS Tecnologia
 *
 */
public class InsertException extends DAOException {

	public InsertException() {
		super("ERRO - Não foi possível inserir. Um erro aconteceu ao manipular ou conectar com o banco de dados.");
	}
	
}