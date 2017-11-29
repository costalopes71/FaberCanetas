package br.com.fabercanetas.to;

import java.io.Serializable;

public class Departamento implements Serializable {
	private static final long serialVersionUID = -9108940742983171956L;

	private int codigo;
	private String nome;
	
	public Departamento() {

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
