package br.com.ciro.vacinacao.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ciro.vacinacao.entity.Pessoa;
import br.com.ciro.vacinacao.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping
	@Transactional
	public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.inserir(pessoa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().buildAndExpand(pessoaSalva.getEmail()).toUri();

		return ResponseEntity.created(uri).body(pessoaSalva);
	}

	@PostMapping("/{nomeVacina}")
	@Transactional
	public ResponseEntity<Pessoa> vacinar(@Valid @PathVariable String nomeVacina, @Valid @RequestParam String email) {
		Pessoa pessoa = pessoaService.vacinarPessoa(email, nomeVacina);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().buildAndExpand(nomeVacina).toUri();
		return ResponseEntity.created(uri).body(pessoa);
	}

}
