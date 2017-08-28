package br.com.plataforma.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma.ws.model.Agenda;
import br.com.plataforma.ws.repository.AgendaRepository;

@Service
public class AgendaService {
	
	@Autowired
	AgendaRepository agendaRepository;
	
	public Agenda cadastrar(Agenda agenda) {

		return agendaRepository.save(agenda);
	}

	public Collection<Agenda> buscarTodos() {
		return agendaRepository.findAll();
	}
	
	public void excluir(Agenda agenda) {
		agendaRepository.delete(agenda);

	}
	
	public Agenda buscarPorId(Integer id) {
		return agendaRepository.findOne(id);
	}
	
	public Agenda alterar(Agenda agenda){
		return agendaRepository.save(agenda);
	}

}
