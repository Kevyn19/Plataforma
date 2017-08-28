package br.com.plataforma.ws.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataforma.ws.model.Avaliacao;
import br.com.plataforma.ws.service.AvaliacaoService;

@RestController
@RequestMapping("/restrito/avaliacao")
public class AvaliacaoController {
	
	@Autowired
	AvaliacaoService avaliacaoService;
	
	@RequestMapping(method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Avaliacao> cadastrarAvaliacao(@RequestBody Avaliacao avaliacao) {

		Avaliacao avaliacaoCadastrado = avaliacaoService.cadastrar(avaliacao);

		return new ResponseEntity<>(avaliacaoCadastrado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Collection<Avaliacao>> buscarTodosAvaliacao() {

		Collection<Avaliacao> avaliacaoBuscados = avaliacaoService.buscarTodos();

		return new ResponseEntity<>(avaliacaoBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Avaliacao> buscarAvaliacaoPorId(@PathVariable Integer id) {

		Avaliacao avaliacaoBuscados = avaliacaoService.buscarPorId(id);

		return new ResponseEntity<>(avaliacaoBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	private ResponseEntity<Avaliacao> excluirAvaliacao(@PathVariable Integer id) {
		
		Avaliacao avaliacaoEncontrado = avaliacaoService.buscarPorId(id);
		
		if(avaliacaoEncontrado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		avaliacaoService.excluir(avaliacaoEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Avaliacao> alterarAvaliacao(@RequestBody Avaliacao avaliacao) {

		Avaliacao avaliacaoAlterado = avaliacaoService.alterar(avaliacao);

		return new ResponseEntity<>(avaliacaoAlterado, HttpStatus.OK);
	}
	

}
