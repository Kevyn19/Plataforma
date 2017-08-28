package br.com.plataforma.ws.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DadosLocalServico implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer IdDadosLocalServico;
	
	private int tamanhoCasa;
	private int nComodos;
	
	@OneToOne
	private UsuarioPlataforma usuario;

	public Integer getIdDadosLocalServico() {
		return IdDadosLocalServico;
	}

	public void setIdDadosLocalServico(Integer idDadosLocalServico) {
		IdDadosLocalServico = idDadosLocalServico;
	}

	public int getTamanhoCasa() {
		return tamanhoCasa;
	}

	public void setTamanhoCasa(int tamanhoCasa) {
		this.tamanhoCasa = tamanhoCasa;
	}

	public int getnComodos() {
		return nComodos;
	}

	public void setnComodos(int nComodos) {
		this.nComodos = nComodos;
	}

	public UsuarioPlataforma getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPlataforma usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	

}
