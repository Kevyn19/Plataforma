package br.com.plataforma.ws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Avaliacao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer IdAvaliacao;
	
	@OneToOne
	private UsuarioPlataforma usuarioPrestador;
	
	@OneToOne
	private UsuarioPlataforma usuarioContratante;
	
	private double nota; 
	
	@Column(columnDefinition="TEXT")
	private String textoAvaliacao;

	public Integer getIdAvaliacao() {
		return IdAvaliacao;
	}

	public void setIdAvaliacao(Integer idAvaliacao) {
		IdAvaliacao = idAvaliacao;
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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getTextoAvaliacao() {
		return textoAvaliacao;
	}

	public void setTextoAvaliacao(String textoAvaliacao) {
		this.textoAvaliacao = textoAvaliacao;
	}
	
	
	

}
