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

import br.com.plataforma.ws.model.Perfil;
import br.com.plataforma.ws.service.PerfilService;

@RestController
@RequestMapping("/restrito/perfil")
public class PerfilController {
	
	@Autowired
	PerfilService perfilService;
	
	@RequestMapping(method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Perfil> cadastrarPerfil(@RequestBody Perfil perfil) {

		Perfil perfilCadastrado = perfilService.cadastrar(perfil);

		return new ResponseEntity<>(perfilCadastrado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Collection<Perfil>> buscarTodosPerfil() {

		Collection<Perfil> perfilBuscados = perfilService.buscarTodos();

		return new ResponseEntity<>(perfilBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Perfil> buscarPerfilPorId(@PathVariable Integer id) {

		Perfil perfilBuscados = perfilService.buscarPorId(id);

		return new ResponseEntity<>(perfilBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	private ResponseEntity<Perfil> excluirPerfil(@PathVariable Integer id) {
		
		Perfil perfilEncontrado = perfilService.buscarPorId(id);
		
		if(perfilEncontrado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		perfilService.excluir(perfilEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Perfil> alterarPerfil(@RequestBody Perfil perfil) {

		Perfil perfilAlterado = perfilService.alterar(perfil);

		return new ResponseEntity<>(perfilAlterado, HttpStatus.OK);
	}

}
