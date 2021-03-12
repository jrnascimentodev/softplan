package com.softplayer.softperson.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	
	@NotEmpty(message ="Preenchimento obrigatório")
	private String nome;
	
	private String sexo;
	
	@Email(message="Email inválido")
	private String email;
	
	@NotNull(message ="Preenchimento obrigatório")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datanascimento;
	
	private String naturalidade;
	private String nacionalidade;
	
	@NotEmpty(message ="Preenchimento obrigatório")
	@CPF
	private String cpf;
	
	private Instant dataCadastro = Instant.now();
	private Instant dataAlteracao;
	
	public Pessoa() {
	}

	public Pessoa(Integer id, 
			      String nome, 
			      String sexo, 
			      String email, 
			      Date datanascimento, 
			      String naturalidade,
			      String nacionalidade, 
			      String cpf,
			      Instant dataCadastro,
			      Instant dataAlteracao) {
		super();
		Id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.email = email;
		this.datanascimento = datanascimento;
		this.naturalidade = naturalidade;
		this.nacionalidade = nacionalidade;
		this.cpf = cpf;
		this.dataCadastro = dataCadastro;
		this.dataAlteracao = dataAlteracao;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Instant getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Instant dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
	
}
