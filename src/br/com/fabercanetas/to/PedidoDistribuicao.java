package br.com.fabercanetas.to;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDistribuicao implements Serializable {
	private static final long serialVersionUID = 6905706114719397583L;

	private long codigo;
	private PedidoVenda pedidoVenda;
	private LocalDate dataSolicitacao;
	private LocalDate dataEnvio;
	private String observacao;
	private List<ItemDistribuicao> listaItem;
	
	public PedidoDistribuicao() {
		listaItem = new ArrayList<>();
	}
	
	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}
	
	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}
	
	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}
	
	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public LocalDate getDataEnvio() {
		return dataEnvio;
	}
	
	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public List<ItemDistribuicao> getListaItem() {
		return listaItem;
	}
	
	public void setListaItem(List<ItemDistribuicao> listaItem) {
		this.listaItem = listaItem;
	}
	
}
