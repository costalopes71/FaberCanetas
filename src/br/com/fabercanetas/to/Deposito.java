package br.com.fabercanetas.to;

import java.io.Serializable;
import java.util.ArrayList;

public class Deposito implements Serializable {
	private static final long serialVersionUID = 1939403771858643995L;

	private int codigo;
	private String nome;
	private int cep;
	private ArrayList<ItemEstoque> listaProduto;
	
	public Deposito() {
		listaProduto = new ArrayList<ItemEstoque>();
	}
	
	public ArrayList<ItemEstoque> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(ArrayList<ItemEstoque> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCep() {
		return cep;
	}
	
	public void setCep(int cep) {
		this.cep = cep;
	}
	
}
