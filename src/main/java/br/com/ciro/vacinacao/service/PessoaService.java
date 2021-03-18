package br.com.ciro.vacinacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ciro.vacinacao.entity.Pessoa;
import br.com.ciro.vacinacao.entity.Vacina;
import br.com.ciro.vacinacao.exceptionhandler.ErroException;
import br.com.ciro.vacinacao.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private VacinaService vacinaService;

	public void procurarPessoaPorEmailCpf(String email, String cpf) {
		{
			Pessoa pessoa = pessoaRepository.findByCpf(cpf);
			if (pessoa != null) {
				throw new ErroException("Cpf já existente.");
			}
		}
		{
			Pessoa pessoa = pessoaRepository.findByEmail(email);
			if (pessoa != null) {
				throw new ErroException("E-mail já existente.");
			}
		}
	}

	public Pessoa inserir(Pessoa pessoa) {
		this.procurarPessoaPorEmailCpf(pessoa.getEmail(), pessoa.getCpf());
		return pessoaRepository.save(pessoa);
	}

	public Pessoa vacinarPessoa(String email, String nomeVacina) {
		Pessoa pessoa = pessoaRepository.findByEmail(email);
		if (pessoa == null) {
			throw new ErroException("Email não cadastrado. ");
		}
		vacinaService.vacinarPessoa(pessoa, nomeVacina);
		return pessoa;
	}
}
