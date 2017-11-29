package br.com.fabercanetas.to;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class OrdemCompra implements Serializable {
	private static final long serialVersionUID = 432341906969791614L;
	
	private int codigo;
	private LocalDate dataSolicitacao;
	private String status;
	private LocalDate dataRecebimento;
	private String observacao;
	private int prazoEntrega;
	private SolicitacaoCompra solicitacaoCompra;
	private Fornecedor fornecedor;
	private List<ItemCompraMP> listaItem;

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}
	
	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}
	
	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public int getPrazoEntrega() {
		return prazoEntrega;
	}
	
	public void setPrazoEntrega(int prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	
	public SolicitacaoCompra getSolicitacaoCompra() {
		return solicitacaoCompra;
	}
	
	public void setSolicitacaoCompra(SolicitacaoCompra solicitacaoCompra) {
		this.solicitacaoCompra = solicitacaoCompra;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ItemCompraMP> getListaItem() {
		return listaItem;
	}

	public void setListaItem(List<ItemCompraMP> listaItem) {
		this.listaItem = listaItem;
	}

}
