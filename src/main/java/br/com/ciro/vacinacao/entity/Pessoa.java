package br.com.ciro.vacinacao.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Email(message = "E-mail inválido")
	@Column(name = "EMAIL")
	private String email;

	@NotNull
	@Column(name = "NOME")
	private String nome;

	@NotNull
	@CPF(message = "CPF inválido")
	@Column(name = "CPF")
	private String cpf;

	@NotNull
	@Column(name = "DATA_NASCIMENTO")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "pessoa")
	private List<Vacina> vacinas = new ArrayList<>();

	public Pessoa(String nome, String email, String cpf, LocalDate dataNascimento) {

		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public Pessoa() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void adicionarVacinaPessoa(Vacina vacina) {
		this.vacinas.add(vacina);

	}

}