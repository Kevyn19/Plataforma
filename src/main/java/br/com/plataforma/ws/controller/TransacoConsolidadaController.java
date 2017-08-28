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

import br.com.plataforma.ws.model.TransacoConsolidada;
import br.com.plataforma.ws.service.TransacoConsolidadaService;

@RestController
@RequestMapping("/restrito/transacoConsolidada")
public class TransacoConsolidadaController {
	
	@Autowired
	TransacoConsolidadaService transacoConsolidadaService;
	
	@RequestMapping(method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<TransacoConsolidada> cadastrarTransacoConsolidada(@RequestBody TransacoConsolidada transacoConsolidada) {

		TransacoConsolidada TransacoConsolidadaCadastrado = transacoConsolidadaService.cadastrar(transacoConsolidada);

		return new ResponseEntity<>(TransacoConsolidadaCadastrado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Collection<TransacoConsolidada>> buscarTodosTransacoConsolidada() {

		Collection<TransacoConsolidada> transacoConsolidadaBuscados = transacoConsolidadaService.buscarTodos();

		return new ResponseEntity<>(transacoConsolidadaBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<TransacoConsolidada> buscarTransacoConsolidadaPorId(@PathVariable Integer id) {

		TransacoConsolidada transacoConsolidadaBuscados = transacoConsolidadaService.buscarPorId(id);

		return new ResponseEntity<>(transacoConsolidadaBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	private ResponseEntity<TransacoConsolidada> excluirTransacoConsolidada(@PathVariable Integer id) {
		
		TransacoConsolidada transacoConsolidadaEncontrado = transacoConsolidadaService.buscarPorId(id);
		
		if(transacoConsolidadaEncontrado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		transacoConsolidadaService.excluir(transacoConsolidadaEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<TransacoConsolidada> alterarTransacoConsolidada(@RequestBody TransacoConsolidada transacoConsolidada) {

		TransacoConsolidada transacoConsolidadaAlterado = transacoConsolidadaService.alterar(transacoConsolidada);

		return new ResponseEntity<>(transacoConsolidadaAlterado, HttpStatus.OK);
	}
	

}
