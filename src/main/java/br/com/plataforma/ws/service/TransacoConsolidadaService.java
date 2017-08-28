package br.com.plataforma.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma.ws.model.TransacoConsolidada;
import br.com.plataforma.ws.repository.TransacoConsolidadaRepository;

@Service
public class TransacoConsolidadaService {
	
	@Autowired
	TransacoConsolidadaRepository transacoConsolidadaRepository;
	
	public TransacoConsolidada cadastrar(TransacoConsolidada transacoConsolidada) {

		return transacoConsolidadaRepository.save(transacoConsolidada);
	}

	public Collection<TransacoConsolidada> buscarTodos() {
		return transacoConsolidadaRepository.findAll();
	}
	
	public void excluir(TransacoConsolidada transacoConsolidada) {
		transacoConsolidadaRepository.delete(transacoConsolidada);

	}
	
	public TransacoConsolidada buscarPorId(Integer id) {
		return transacoConsolidadaRepository.findOne(id);
	}
	
	public TransacoConsolidada alterar(TransacoConsolidada transacoConsolidada){
		return transacoConsolidadaRepository.save(transacoConsolidada);
	}

}
