package br.com.fabercanetas.exceptions;

@SuppressWarnings("serial")
/**
 * Classe de exceção quando da tentativa de validar dados de login do Banco de Dados.
 * @author TDS Tecnologia
 *
 */
public class ValidateLoginException extends DAOException {

	public ValidateLoginException() {
		super("ERRO - Não foi possível validar os dados de login. Um erro aconteceu ao manipular ou conectar com o banco de dados.");
	}

}