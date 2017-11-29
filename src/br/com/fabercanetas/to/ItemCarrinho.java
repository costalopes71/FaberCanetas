package br.com.fabercanetas.to;

import java.io.Serializable;

/**
 * Classe que representa uma entidade ItemCarrinho. 
 * @author TDS Tecnologia
 *
 */
public class ItemCarrinho extends Produto implements Serializable {
	private static final long serialVersionUID = -7214873547470808455L;
	
	private int qtd = 1;
	private double valorTotalItem;

	public ItemCarrinho() {
		super();
	}
	
	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
		this.valorTotalItem = this.qtd * this.getValorUnitario();
	}

	public double getValorTotalItem() {
		return this.valorTotalItem;
	}
	
	public void setValorTotalItem(double valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}
	
}
