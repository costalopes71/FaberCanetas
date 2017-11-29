package br.com.fabercanetas.exceptions;

@SuppressWarnings("serial")
/**
 * Classe de exceção quando da tentativa de recuperar algum dado do Banco de Dados.
 * @author TDS Tecnologia
 *
 */
public class RetrieveException extends DAOException {

	public RetrieveException() {
		super("ERRO - Não foi possível recuperar os dados. Um erro aconteceu ao manipular ou conectar com o banco de dados.");
	}

}
