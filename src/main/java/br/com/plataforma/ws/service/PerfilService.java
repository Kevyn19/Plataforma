package br.com.plataforma.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma.ws.model.Perfil;
import br.com.plataforma.ws.repository.PerfilRepository;

@Service
public class PerfilService {
	
	@Autowired
	PerfilRepository perfilRepository;
	
	public Perfil cadastrar(Perfil perfil) {

		return perfilRepository.save(perfil);
	}

	public Collection<Perfil> buscarTodos() {
		return perfilRepository.findAll();
	}
	
	public void excluir(Perfil perfil) {
		perfilRepository.delete(perfil);

	}
	
	public Perfil buscarPorId(Integer id) {
		return perfilRepository.findOne(id);
	}
	
	public Perfil alterar(Perfil perfil){
		return perfilRepository.save(perfil);
	}

}
