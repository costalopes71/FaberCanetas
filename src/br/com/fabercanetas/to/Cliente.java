package br.com.fabercanetas.to;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que representa uma entidade Cliente.
 * @author TDS Tecnologia
 *
 */
public class Cliente implements Serializable {
	private static final long serialVersionUID = -9125456811237323972L;
	
	private int codigo;
	private String razaoSocial;
	private String nomeFantasia;
	private long cnpj;
	private long inscricaoEstadual;
	private long inscricaoMunicipal;
	private String email;
	private LocalDate dataCadastro;
	private String senha;
	
	public Cliente() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int idCliente) {
		this.codigo = idCliente;
	}
	
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	
	public void setNomeFantasia(String nmFantasia) {
		this.nomeFantasia = nmFantasia;
	}
	
	public long getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
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

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public long getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(long inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public long getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(long inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
