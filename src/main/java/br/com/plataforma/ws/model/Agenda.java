package br.com.plataforma.ws.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Agenda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer IdAgenda;
	
	private String data;
	private int status;
	private int manhaOuTardeDiaria;

	@OneToOne
	private UsuarioPlataforma usuarioPrestador;
	
	public UsuarioPlataforma getUsuario() {
		return usuarioPrestador;
	}
	public void setUsuario(UsuarioPlataforma usuario) {
		this.usuarioPrestador = usuario;
	}
	public Integer getIdAgenda() {
		return IdAgenda;
	}
	public void setIdAgenda(Integer idAgenda) {
		IdAgenda = idAgenda;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UsuarioPlataforma getUsuarioPrestador() {
		return usuarioPrestador;
	}
	public void setUsuarioPrestador(UsuarioPlataforma usuarioPrestador) {
		this.usuarioPrestador = usuarioPrestador;
	}
	public int getManhaOuTardeDiaria() {
		return manhaOuTardeDiaria;
	}
	public void setManhaOuTardeDiaria(int manhaOuTardeDiaria) {
		this.manhaOuTardeDiaria = manhaOuTardeDiaria;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	

}
