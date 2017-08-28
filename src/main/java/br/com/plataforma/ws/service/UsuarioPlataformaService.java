package br.com.plataforma.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma.ws.model.DadosLocalServico;
import br.com.plataforma.ws.model.UsuarioPlataforma;
import br.com.plataforma.ws.repository.UsuarioPlataformaRepository;

@Service
public class UsuarioPlataformaService {
	
	@Autowired
	private UsuarioPlataformaRepository usuarioRepository;
	
	public UsuarioPlataforma cadastrar(UsuarioPlataforma usuario){
		return usuarioRepository.save(usuario);
	}
	
	public UsuarioPlataforma buscarPorLogin(String login){
		return usuarioRepository.bsucarPorLogin(login);
	}
	
	public UsuarioPlataforma buscarPorId(Integer id) {
		return usuarioRepository.findOne(id);
	}
	
	public boolean existeUsuarioNaBase(String login){
		UsuarioPlataforma user = usuarioRepository.bsucarPorLogin(login);
		
		if(user != null && !user.getLogin().equals("")){
			return true;
		}else{
			return false;
		}
			
	}

}
