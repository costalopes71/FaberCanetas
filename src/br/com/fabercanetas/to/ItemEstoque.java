package br.com.fabercanetas.to;

import java.io.Serializable;

public class ItemEstoque implements Serializable {

	private static final long serialVersionUID = -68025937914070527L;
	private int codigo;
	private String produto;
	private long quantidade;
	private long quantidadeMinima;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getProduto() {
		return produto;
	}
	
	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	public long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	
	public long getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(long quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}
	
}
