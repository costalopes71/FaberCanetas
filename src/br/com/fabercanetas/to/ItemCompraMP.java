package br.com.fabercanetas.to;

import java.io.Serializable;

public class ItemCompraMP implements Serializable {
	private static final long serialVersionUID = -8858459428117047257L;
	
	private OrdemCompra ordemCompra;
	private double valorUnitario;
	private int quantidade;
	private int codigoItemEstoque;
	private Produto produto;
	
	public OrdemCompra getOrdemCompra() {
		return ordemCompra;
	}
	
	public void setOrdemCompra(OrdemCompra ordemCompra) {
		this.ordemCompra = ordemCompra;
	}
	
	public double getValorUnitario() {
		return valorUnitario;
	}
	
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getCodigoItemEstoque() {
		return codigoItemEstoque;
	}
	
	public void setCodigoItemEstoque(int codigoItemEstoque) {
		this.codigoItemEstoque = codigoItemEstoque;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
