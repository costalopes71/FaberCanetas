package br.com.fabercanetas.to;

import java.io.Serializable;

/**
 * Classe que representa uma entidade Item de Venda.
 * @author TDS Tecnologia
 *
 */
public class ItemVenda implements Serializable {
	private static final long serialVersionUID = -2625547302003171810L;

	private PedidoVenda pedido;
	private int quantidade;
	private double valorUnitario;
	private double valorTotalItem;
	private Produto produto;
	
	public ItemVenda() {
		
	}
	
	public PedidoVenda getPedido() {
		return pedido;
	}
	
	public void setPedido(PedidoVenda pedido) {
		this.pedido = pedido;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getValorTotalItem() {
		return valorTotalItem;
	}
	
	public void setValorTotalItem(double valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
		this.setValorUnitario(produto.getValorUnitario());
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
