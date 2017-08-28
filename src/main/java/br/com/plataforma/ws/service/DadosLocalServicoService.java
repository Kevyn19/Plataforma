package br.com.plataforma.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma.ws.model.DadosLocalServico;
import br.com.plataforma.ws.repository.DadosLocalServicoRepository;

@Service
public class DadosLocalServicoService {
	
	@Autowired
	DadosLocalServicoRepository dadosLocalServicoRepository;
	
	public DadosLocalServico cadastrar(DadosLocalServico dadosLocalServico) {

		return dadosLocalServicoRepository.save(dadosLocalServico);
	}

	public Collection<DadosLocalServico> buscarTodos() {
		return dadosLocalServicoRepository.findAll();
	}
	
	public void excluir(DadosLocalServico dadosLocalServico) {
		dadosLocalServicoRepository.delete(dadosLocalServico);

	}

	public DadosLocalServico alterar(DadosLocalServico dadosLocalServico){
		return dadosLocalServicoRepository.save(dadosLocalServico);
	}
	
	public DadosLocalServico buscarPorUsuario(Integer id) {
		return dadosLocalServicoRepository.buscarPorUsuario(id);

	}


}
