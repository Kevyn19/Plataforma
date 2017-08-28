package br.com.plataforma.ws.utils;

import javax.servlet.ServletException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class KeyToken {

	public final static String KEY_TOEKN = "xavasca";

	public static String pegarUsuarioLogado(String tokenParam) throws ServletException {

		String header = tokenParam;

		if (header == null || !header.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou inválido");
		}

		String token = header.substring(7); // Extrai apenas a string do token!

		Claims loged = null;
		try {
			loged = Jwts.parser().setSigningKey(KEY_TOEKN).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return "401";

		}

		return loged.get("sub").toString();
	}
	
	public static boolean isUsuarioTokenValido(String login) throws ServletException{
		boolean retorno = false;
		if(login != null && !login.equals("401")){
			retorno = true;
		}
		
		return retorno;
	}

}
