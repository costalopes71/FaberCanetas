package br.com.fabercanetas.bo;

import java.sql.SQLException;

import br.com.fabercanetas.dao.ClienteDAO;
import br.com.fabercanetas.exceptions.DeleteException;
import br.com.fabercanetas.exceptions.InsertException;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.exceptions.UpdateException;
import br.com.fabercanetas.to.Cliente;

/**
* Classe que acessa o banco de dados e retorna dados do cliente ou faz querys. CRUD completo do beans Cliente.
 * @author TDS Tecnologia
 * @version 1.1
 */
public class ClienteBO {

	/**
	 * Método que inseri um novo cliente no Banco de Dados. Espera por parametro o Objeto Cliente.
	 * Para tanto utiliza o metodo inserirCliente da classe de persistência ClienteDAO.
	 * @param Cliente (cliente a ser inserido)
	 * @throws InsertException
	 */
	public void inserirCliente(Cliente cliente) throws InsertException {
		
		try {
			new ClienteDAO().inserirCliente(cliente);
		} catch (SQLException e) {
			throw new InsertException();
		}
	}
	
	/**
	 * Método que pesquisa um cliente no banco e retorna os dados do cliente através de um objeto Cliente.
	 * O parâmetro que deve ser passado para a pesquisa é o id do cliente.  
	 * Para tanto utiliza o metodo buscarCliente da classe de persistência ClienteDAO.
	 * @param idCLiente (int)
	 * @return Cliente (retorna um objeto Cliente com os dados do cliente pesquisado)
	 * @throws RetrieveException
	 */
	public Cliente buscarCliente(int idCliente) throws RetrieveException {
		
		try {
			return new ClienteDAO().buscarCliente(idCliente);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	/**
	 * Método que pesquisa um cliente no banco e retorna os dados do cliente através de um objeto Cliente.
	 * O parâmetro que deve ser passado para a pesquisa é o e-mail do cliente e a senha.
	 * Para tanto utiliza o metodo buscarClienteEmailSenha da classe de persistência ClienteDAO.
	 * @param email (e-mail do Cliente), senha (senha do cliente)
	 * @return Cliente (retorna um objeto Cliente)
	 * @throws RetrieveException
	 */
	public Cliente buscarCliente(String email, String senha) throws RetrieveException {
		try {
			return new ClienteDAO().buscarCliente(email, senha);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	/**
	 * Método que pesquisa um cliente no banco e retorna os dados do cliente através de um objeto Cliente.
	 * O parâmetro que deve ser passado para a pesquisa é o e-mail do cliente.
	 * Para tanto utiliza o metodo buscarClienteEmail da classe de persistência ClienteDAO.
	 * @param email (e-mail do Cliente)
	 * @return Cliente (retorna um objeto Cliente)
	 * @throws RetrieveException
	 */
	public Cliente buscarCliente(String email) throws RetrieveException {
		try {
			return new ClienteDAO().buscarCliente(email);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	/**
	 * Método que atualiza as informações de um determinado cliente.
	 * Para tanto utiliza o metodo atualizarCliente da classe de persistência ClienteDAO.
	 * @param Cliente (objeto Cliente a ser atualizado)
	 * @throws UpdateException
	 */
	public void atualizarCliente(Cliente cliente) throws UpdateException {
	
		try {
			new ClienteDAO().atualizarCliente(cliente);
		} catch (SQLException e) {
			throw new UpdateException();
		}
	}
	
	/**
	 * Método que exclui um cliente. Espera como parâmetro o inteiro id do cliente a ser excluído.
	 * Para tanto utiliza o metodo deletarCliente da classe de persistência ClienteDAO.
	 * @param int idCliente
	 * @throws DeleteException 
	 * @throws DeleteException
	 */
	public void deletarCliente(int idCliente) throws DeleteException {
		
		try {
			new ClienteDAO().deletarCliente(idCliente);
		} catch (SQLException e) {
			throw new DeleteException();
		}
	}
	
	/**
	 * Método que valida o login do usuário. Acessa o banco de dados e retorna um booleano, true se o login
	 * existir e o e-mail e senha forem válidos e false se o login não existir ou um dos dois parâmetros estiverem errados.
	 * Para tanto utiliza o metodo validaLogin da classe de persistência ClienteDAO.
	 * @param email String, e-mail do usuario (login)
	 * @param senha	String, senha do usuario
	 * @return boolean (true se o login for valido, false se for invalido)
	 * @throws ValidateLoginException
	 */
//	public boolean validaLogin(String email, String senha) throws ValidateLoginException {
//
//		try {
//		ClienteDAO clienteDAO = new ClienteDAO();
//		return clienteDAO.validaLogin(email, senha);
//		} catch (SQLException e) {
//			throw new ValidateLoginException();
//		}
//	}
	
}
