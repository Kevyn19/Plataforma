package br.com.plataforma.ws.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Perfil implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private Integer IdPerfil;
	private String perfil;
	
	public Integer getIdPerfil() {
		return IdPerfil;
	}
	public void setIdPerfil(Integer idPerfil) {
		IdPerfil = idPerfil;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	

}
