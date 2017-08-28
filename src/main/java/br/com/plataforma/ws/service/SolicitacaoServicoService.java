package br.com.plataforma.ws.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma.ws.model.SolicitacaoServico;
import br.com.plataforma.ws.repository.SolicitacaoServicoRepository;

@Service
public class SolicitacaoServicoService {
	
	@Autowired
	SolicitacaoServicoRepository solicitacaoServicoRepository;
	
	public SolicitacaoServico cadastrar(SolicitacaoServico solicitacaoServico) {

		return solicitacaoServicoRepository.save(solicitacaoServico);
	}

	public Collection<SolicitacaoServico> buscarTodos() {
		return solicitacaoServicoRepository.findAll();
	}
	
	public void excluir(SolicitacaoServico solicitacaoServico) {
		solicitacaoServicoRepository.delete(solicitacaoServico);

	}
	
	public SolicitacaoServico buscarPorId(Integer id) {
		return solicitacaoServicoRepository.findOne(id);
	}
	
	public SolicitacaoServico alterar(SolicitacaoServico solicitacaoServico){
		return solicitacaoServicoRepository.save(solicitacaoServico);
	}
	
	public List<SolicitacaoServico> buscarPorUsuarioPrestador(Integer id) {
		return solicitacaoServicoRepository.buscarPorUsuarioPrestador(id);

	}

}
