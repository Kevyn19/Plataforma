package br.com.plataforma.ws.controller;

import java.util.Date;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataforma.ws.model.UsuarioPlataforma;
import br.com.plataforma.ws.service.UsuarioPlataformaService;
import br.com.plataforma.ws.utils.KeyToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	
	@Autowired
	private UsuarioPlataformaService usuarioService;
	
	/**
	 * Faz o login na aplicação
	 * @param usuario
	 * @return
	 * @throws SerialException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse autenticar(@RequestBody UsuarioPlataforma usuario) throws SerialException{

		if(usuario.getLogin() == null || usuario.getSenha() == null ){
			throw new SerialException("login e senha são obrigatórios");
		}
		
		UsuarioPlataforma userAutenticado = usuarioService.buscarPorLogin(usuario.getLogin());
		
		if(userAutenticado == null){
			throw new SerialException("Usuario não encontrado");
		}
		
		if(!userAutenticado.getSenha().equals(usuario.getSenha())){
			throw new SerialException("Usuario ou senha inválida");
		}
		
		String token = Jwts.builder().
				setSubject(userAutenticado.getLogin()).
				signWith(SignatureAlgorithm.HS256, KeyToken.KEY_TOEKN).
				setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000 )).
				compact();
		
		return new LoginResponse(token);
		
	}
	
	private class LoginResponse{
		public String token;
		
		public LoginResponse(String token){
			this.token = token;
		}
		
	}
	

}
