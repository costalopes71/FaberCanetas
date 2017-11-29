package br.com.fabercanetas.to;

import java.io.Serializable;

public class ItemDistribuicao implements Serializable {
	private static final long serialVersionUID = 3875526899060525391L;
	
	private PedidoDistribuicao pedido;
	private int quantidade;
	private Produto produto;
	private double valorUnitario;
	private int codigoItemEstoque;
	
	public PedidoDistribuicao getPedido() {
		return pedido;
	}
	
	public void setPedido(PedidoDistribuicao pedido) {
		this.pedido = pedido;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public double getValorUnitario() {
		return valorUnitario;
	}
	
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public int getCodigoItemEstoque() {
		return codigoItemEstoque;
	}

	public void setCodigoItemEstoque(int codigoItemEstoque) {
		this.codigoItemEstoque = codigoItemEstoque;
	}
	
}
