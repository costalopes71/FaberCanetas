package br.com.fabercanetas.to;

import java.io.Serializable;

/**
 * Classe que representa uma entidade Produto.
 * @author TDS Tecnologia
 *
 */
public class Produto implements Serializable {
	private static final long serialVersionUID = -3773941679253750217L;
	
	private int codigo;
	private String descricaoProduto;
	private String unidadeMedida;
	private double valorUnitario;
	private String nomeProduto;
	private String urlImgProduto;
	private String descricaoProdutoCompleta;
	private String descricaoSimples;
	
	public Produto() {
		
	}

	public Produto(int codigo, String descricaoProduto, String unidadeMedida, double valorUnitario, String nomeProduto,
			String urlImgProduto, String descricaoProdutoCompleta, String descricaoSimples) {
		super();
		this.codigo = codigo;
		this.descricaoProduto = descricaoProduto;
		this.unidadeMedida = unidadeMedida;
		this.valorUnitario = valorUnitario;
		this.nomeProduto = nomeProduto;
		this.urlImgProduto = urlImgProduto;
		this.descricaoProdutoCompleta = descricaoProdutoCompleta;
		this.descricaoSimples = descricaoSimples;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getUrlImgProduto() {
		return urlImgProduto;
	}

	public void setUrlImgProduto(String urlImgProduto) {
		this.urlImgProduto = urlImgProduto;
	}

	public String getDescricaoProdutoCompleta() {
		return descricaoProdutoCompleta;
	}

	public void setDescricaoProdutoCompleta(String descricaoProdutoCompleta) {
		this.descricaoProdutoCompleta = descricaoProdutoCompleta;
	}

	public String getDescricaoSimples() {
		return descricaoSimples;
	}

	public void setDescricaoSimples(String descricaoSimples) {
		this.descricaoSimples = descricaoSimples;
	}
	
}
