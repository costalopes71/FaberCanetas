package br.com.fabercanetas.to;

/**
 * Classe que representa uma entidade Item de Relatorio Mensal.
 * @author TDS Tecnologia
 *
 */
public class RelatorioItem {

	private String nomeProduto;
	private int quantidade;

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
