package br.com.plataforma.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma.ws.model.Avaliacao;
import br.com.plataforma.ws.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	

	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	public Avaliacao cadastrar(Avaliacao avaliacao) {

		return avaliacaoRepository.save(avaliacao);
	}

	public Collection<Avaliacao> buscarTodos() {
		return avaliacaoRepository.findAll();
	}
	
	public void excluir(Avaliacao avaliacao) {
		avaliacaoRepository.delete(avaliacao);

	}
	
	public Avaliacao buscarPorId(Integer id) {
		return avaliacaoRepository.findOne(id);
	}
	
	public Avaliacao alterar(Avaliacao avaliacao){
		return avaliacaoRepository.save(avaliacao);
	}


}
