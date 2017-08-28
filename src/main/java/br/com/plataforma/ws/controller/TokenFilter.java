package br.com.plataforma.ws.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import br.com.plataforma.ws.utils.KeyToken;
import io.jsonwebtoken.Jwts;

public class TokenFilter extends GenericFilterBean {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String header = req.getHeader("Authorization");
		
		if(header == null || !header.startsWith("Bearer ")){
			throw new ServletException("Token inexistente ou inválido");
		}
		
		String token = header.substring(7); //Extrai apenas a string do token!
		
		//Verifica a integridade do token
		try {
			Jwts.parser().setSigningKey(KeyToken.KEY_TOEKN).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token inválido");
			//throw new ServletException("Token Inválido");
		}
		
		//
		chain.doFilter(request, response);
		
	}

}
