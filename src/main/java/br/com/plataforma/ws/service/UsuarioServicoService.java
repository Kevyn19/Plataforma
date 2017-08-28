package br.com.plataforma.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma.ws.model.UsuarioServico;
import br.com.plataforma.ws.repository.UsuarioServicoRepository;

@Service
public class UsuarioServicoService {
	
	@Autowired
	UsuarioServicoRepository usuarioServicoRepository;
	
	public UsuarioServico cadastrar(UsuarioServico usuarioServico) {

		return usuarioServicoRepository.save(usuarioServico);
	}

	public Collection<UsuarioServico> buscarTodos() {
		return usuarioServicoRepository.findAll();
	}
	
	public void excluir(UsuarioServico usuarioServico) {
		usuarioServicoRepository.delete(usuarioServico);

	}
	
	public UsuarioServico buscarPorId(Integer id) {
		return usuarioServicoRepository.findOne(id);
	}
	
	public UsuarioServico alterar(UsuarioServico usuarioServico){
		return usuarioServicoRepository.save(usuarioServico);
	}
	
	public UsuarioServico buscarPorUsuario(Integer id) {
		return usuarioServicoRepository.buscarPorUsuario(id);

	}

	public UsuarioServico buscarPorDiariaOuMeia(Integer id, Integer diariaOuMeia) {
		return usuarioServicoRepository.buscarPorDiariaOuMeia(id,diariaOuMeia);
	}


}
