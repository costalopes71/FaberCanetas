package br.com.fabercanetas.to;

import java.io.Serializable;

public class Funcao implements Serializable {
	private static final long serialVersionUID = 1469023831905789636L;
	
	private int codigo;
	private String nome;
	
	public Funcao() {

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
