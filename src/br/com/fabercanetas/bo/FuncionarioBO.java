package br.com.fabercanetas.bo;

import java.sql.SQLException;

import br.com.fabercanetas.dao.FuncionarioDAO;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.to.Funcionario;

public class FuncionarioBO {

	public Funcionario buscarFuncionario(int codigoFuncionario) throws RetrieveException {
		try {
			return new FuncionarioDAO().buscarFuncionario(codigoFuncionario);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	/**
	 * Método que pesquisa um funcionario no banco de dados através da Classe FuncionarioDAO e retorna os dados do funcionario através 
	 * de um objeto Funcionario.
	 * O parâmetro que deve ser passado para a pesquisa é o e-mail do funcionário e a senha do funcionário.
	 * @param email (e-mail do funcionário), senha (senha do funcionário)
	 * @return Funcionario (retorna um objeto do tipo Funcionario) ou null se o email e a senha não existirem no banco de dados.
	 * @throws SQLException
	 * @see br.com.fabercanetas.dao.FuncionarioDAO
	 */
	public Funcionario buscarFuncionario(String email, String senha) throws RetrieveException {
		try {
			return new FuncionarioDAO().buscarFuncionario(email, senha);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
}
