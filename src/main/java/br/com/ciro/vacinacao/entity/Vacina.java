package br.com.ciro.vacinacao.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vacina")
public class Vacina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "ID_VACINA")
	private Long idVacina;

	@JoinColumn(name = "NOME")
	private String nome;

	@JoinColumn(name = "EMAIL")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVacinacao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "EMAIL")
	private Pessoa pessoa;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataVacinacao() {
		return dataVacinacao;
	}

	public void setDataVacinacao(LocalDate localDate) {
		this.dataVacinacao = localDate;
	}

	public void aplicarVacina(Pessoa pessoa, String nomeVacina) {
		this.nome = nomeVacina;
		this.pessoa = pessoa;
		this.dataVacinacao = LocalDate.now();
	}

}
