package br.com.plataforma.ws.utils;

import br.com.plataforma.ws.model.DadosPessoais;
import br.com.plataforma.ws.model.UsuarioPlataforma;

public class Validador {
	
	//Valida usuario
	public static boolean isUsuarioValido(UsuarioPlataforma user){
		boolean result = false;
		if(user != null && !user.getLogin().equals("") && !user.getSenha().equals("")){
			result = true;
		}
		
		return result;
	}
	
	//Valida usuario prestador
	public static boolean isUsuarioPrestadorValido(UsuarioPlataforma user){
		
		boolean result = false;
		if (user != null && !user.getLogin().equals("") && !user.getSenha().equals("") && user.getPerfil().getIdPerfil() == 1) {
			result = true;
		}
		
		return result;
	}
	
	//Valida usuario solicitante
	public static boolean isUsuarioSolicitanteValido(UsuarioPlataforma user){ 
		boolean result = false;
		if (user != null && !user.getLogin().equals("") && !user.getSenha().equals("") && user.getPerfil().getIdPerfil() == 2) {
			result = true;
		}
		
		return result;
	}
	
	public static boolean isDadosPessoaisValidos(DadosPessoais dadosPessoais){
		boolean result = false;
		
		if(dadosPessoais != null && (dadosPessoais.getRuaOuAv() != null && dadosPessoais.getRuaOuAv() != "")
				&& (dadosPessoais.getBairro() != null && dadosPessoais.getBairro() != "")
				&& (dadosPessoais.getNumero() != 0)
				&& (dadosPessoais.getUf() != null && dadosPessoais.getUf() != "")
				&& (dadosPessoais.getCidade() != null && dadosPessoais.getCidade() != "")){
			result = true;
			
		}
		
		return result;
		
	}

}
