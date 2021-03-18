package br.com.ciro.vacinacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ciro.vacinacao.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	Pessoa findByEmail(String email);
	Pessoa findByCpf(String cpf);
}
