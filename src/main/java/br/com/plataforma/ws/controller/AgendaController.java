package br.com.plataforma.ws.controller;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataforma.ws.model.Agenda;
import br.com.plataforma.ws.model.UsuarioPlataforma;
import br.com.plataforma.ws.service.AgendaService;
import br.com.plataforma.ws.service.UsuarioPlataformaService;
import br.com.plataforma.ws.utils.Conversor;
import br.com.plataforma.ws.utils.KeyToken;

@RestController
@RequestMapping("/restrito/agenda")
public class AgendaController {
	
	@Autowired
	AgendaService agendaService;


	@RequestMapping(method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Collection<Agenda>> buscarTodosAgenda() {

		Collection<Agenda> AgendaBuscados = agendaService.buscarTodos();

		return new ResponseEntity<>(AgendaBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Agenda> buscarAgendaPorId(@PathVariable Integer id) {

		Agenda agendaBuscados = agendaService.buscarPorId(id);

		return new ResponseEntity<>(agendaBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	private ResponseEntity<Agenda> excluirAgenda(@PathVariable Integer id) {
		
		Agenda agendaEncontrado = agendaService.buscarPorId(id);
		
		if(agendaEncontrado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		agendaService.excluir(agendaEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Agenda> alterarAgenda(@RequestBody Agenda agenda) {

		Agenda agendaAlterado = agendaService.alterar(agenda);

		return new ResponseEntity<>(agendaAlterado, HttpStatus.OK);
	}

}
