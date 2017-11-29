package br.com.fabercanetas.to;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe que representa uma entidade Pedido de Venda.
 * @author TDS Tecnologia
 *
 */
public class PedidoVenda implements Serializable {
	private static final long serialVersionUID = -5539384060822265275L;

	private int codigo;
	private Cliente cliente;
	private Funcionario funcionario;
	private LocalDate dataPedido;
	private String statusPedido;
	private int prazoEntrega;
	private double valorFrete;
	private ArrayList<ItemVenda> listaItem;
	private String obsPedido;
	
	public PedidoVenda() {
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public LocalDate getDataPedido() {
		return dataPedido;
	}
	
	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public String getStatusPedido() {
		return statusPedido;
	}
	
	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	public int getPrazoEntrega() {
		return prazoEntrega;
	}
	
	public void setPrazoEntrega(int prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	
	public double getValorFrete() {
		return valorFrete;
	}
	
	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	public ArrayList<ItemVenda> getListaItem() {
		return listaItem;
	}
	
	public void setListaItem(ArrayList<ItemVenda> listaItem) {
		this.listaItem = listaItem;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getObsPedido() {
		return obsPedido;
	}

	public void setObsPedido(String obsPedido) {
		this.obsPedido = obsPedido;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
