package br.com.ciro.vacinacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ciro.vacinacao.entity.Pessoa;
import br.com.ciro.vacinacao.entity.Vacina;
import br.com.ciro.vacinacao.exceptionhandler.ErroException;
import br.com.ciro.vacinacao.repository.VacinaRepository;

@Service
public class VacinaService {

	@Autowired
	private VacinaRepository vacinaRepository;

	public void vacinarPessoa(Pessoa pessoa, String nomeVacina) {
		Vacina vacina = new Vacina();
		vacina.aplicarVacina(pessoa, nomeVacina);
		pessoa.adicionarVacinaPessoa(vacina);
		vacinaRepository.save(vacina);
	}
}
