package br.com.ciro.vacinacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ciro.vacinacao.entity.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
	Vacina findByNome(String nomeVacina);
}
