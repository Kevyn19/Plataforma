package br.com.plataforma.ws.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SolicitacaoServico implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer IdSolicitacaoServico;
	
	@OneToOne
	private UsuarioPlataforma usuarioPrestador;
	
	@OneToOne
	private UsuarioPlataforma usuarioContratante;
	
	private int status;
	
	private String data;
	
	private int manhaOuTardeDiaria;
	
	private int distancia;
	
	

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Integer getIdSolicitacaoServico() {
		return IdSolicitacaoServico;
	}

	public void setIdSolicitacaoServico(Integer idSolicitacaoServico) {
		IdSolicitacaoServico = idSolicitacaoServico;
	}

	public UsuarioPlataforma getUsuarioPrestador() {
		return usuarioPrestador;
	}

	public void setUsuarioPrestador(UsuarioPlataforma usuarioPrestador) {
		this.usuarioPrestador = usuarioPrestador;
	}

	public UsuarioPlataforma getUsuarioContratante() {
		return usuarioContratante;
	}

	public void setUsuarioContratante(UsuarioPlataforma usuarioContratante) {
		this.usuarioContratante = usuarioContratante;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getManhaOuTardeDiaria() {
		return manhaOuTardeDiaria;
	}

	public void setManhaOuTardeDiaria(int manhaOuTardeDiaria) {
		this.manhaOuTardeDiaria = manhaOuTardeDiaria;
	}



}
