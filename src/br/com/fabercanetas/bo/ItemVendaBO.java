package br.com.fabercanetas.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fabercanetas.dao.ItemVendaDAO;
import br.com.fabercanetas.exceptions.DeleteException;
import br.com.fabercanetas.exceptions.InsertException;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.exceptions.UpdateException;
import br.com.fabercanetas.to.ItemVenda;
import br.com.fabercanetas.to.RelatorioItem;

/**
 * Classe que persiste os dados dos itens do pedido de venda no banco de dados.
 * @author TDS Tecnologia
 * @version 1.1
 */
public class ItemVendaBO {

	/**
	 * Método que insere um item de venda e sua respectiva quantidade e valor total no Banco de Dados. Espera por um parametro item que
	 * é um objeto ItemVenda.
	 * @param item ItemVenda
	 * @throws InsertException
	 */
	public void inserirItem(ItemVenda item) throws InsertException {
		try {
			new ItemVendaDAO().inserirItem(item);
		} catch (SQLException e) {
			throw new InsertException();
		}
	}
	
	/**
	 * Método que insere vários itens de venda no banco de dados. Espera como parametro um ArrayList contendo os items de venda (ItemVenda)
	 * @param listaItem ArrayList<ItemVenda>
	 * @throws InsertException
	 */
	public void inserirColecao(ArrayList<ItemVenda> listaItem) throws InsertException {
		try {
			new ItemVendaDAO().inserirColecao(listaItem);
		} catch (SQLException e) {
			throw new InsertException();
		}
	}
	
	/**
	 * Método que retorna um item de determinado pedido de venda. Para tanto é necessário como parametro o código do pedido de venda
	 * e o código do produto.
	 * @param codigoPedido, numero inteiro que representa o codigo do pedido de venda.
	 * @param codigoProduto, numero inteiro que representa o codigo do produto.
	 * @return item ItemVenda, retorna um unico item de venda.
	 * @throws RetrieveException
	 */
	public ItemVenda buscarItemVenda(int codigoPedido, int codigoProduto) throws RetrieveException {
		try {
			return new ItemVendaDAO().buscarItemVenda(codigoPedido, codigoProduto);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	/**
	 * Método que retorna um ArrayList com todos os itens de venda de um determinado pedido de venda. Para tanto é necessário passar
	 * como parametro o número do pedido de venda que se quer recuperar os items.
	 * @param codigoVenda, número inteiro que representa o codigo de venda.
	 * @return listaItem , um ArrayList<ItemVenda> o qual possui todos os itens do pedido de venda.
	 * @throws RetrieveException
	 */
	public ArrayList<ItemVenda> listarItensVenda(int codigoVenda) throws RetrieveException {
		try {
			return new ItemVendaDAO().listarItensVenda(codigoVenda);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	/**
	 * Método que atualiza um item de venda. Para tanto necessita como parametro um objeto ItemVenda que possua as informacoes do
	 * item a ser alterado.
	 * @param item , objeto ItemVenda com os dados a serem alterados.
	 * @throws UpdateException
	 */
	public void atualizarItemVenda(ItemVenda item) throws UpdateException {
		try {
			new ItemVendaDAO().atualizarItemVenda(item);
		} catch (SQLException e) {
			throw new UpdateException();
		}
	}
	
	/**
	 * Método que deleta um item de venda do banco de dados. Para tanto espera como parametro um numero inteiro que representa o codigo
	 * do pedido de venda e um numero inteiro que representa o codigo do produto.
	 * @param codigoPedido , integer que representa o codigo do pedido de venda.
	 * @param codigoProduto , integer que representa o codigo do produto.
	 * @throws DeleteException
	 */
	public void deletarItemVenda(int codigoPedido, int codigoProduto) throws DeleteException {
		try {
			new ItemVendaDAO().deletarItemVenda(codigoPedido, codigoProduto);
		} catch (SQLException e) {
			throw new DeleteException();
		}
	}
	
	/**
	 * Método que retorna um ArrayList contendo todos os produtos e suas respectivas quantidades totais vendidas entre
	 * a data de inicio e fim que o usuario indicou.
	 * @param inicio, uma String que representa a data de inicio desejada. 
	 * @param fim, uma String que representa a data de fim desejada. 
	 * @return relatorioMensal, um ArrayList<RelatorioItem> que contem todos os produtos e suas respectivas quantidades
	 * vendidas entre a data de inicio e data de fim.
	 * @see br.com.fabercanetas.dao.ItemVendaDAO
	 * @throws RetrieveException
	 */
	public ArrayList<RelatorioItem> relatorioMensal(String inicio, String fim) throws RetrieveException {
		try {
			return new ItemVendaDAO().relatorioMensal(inicio, fim);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
}
