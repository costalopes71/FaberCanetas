package br.com.fabercanetas.to;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que representa uma entidade Funcionário.
 * @author TDS Tecnologia
 *
 */
public class Funcionario implements Serializable {
	private static final long serialVersionUID = -6978680327919124073L;
	
	private int codigo;
	private Departamento departamento;
	private Funcao funcao;
	private LocalDate dataCadastro;
	private String email;
	private String senha;
	private String nome;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
