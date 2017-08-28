package br.com.plataforma.ws.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class UsuarioServico implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer IdUsuarioServico;
	
	@OneToOne
	private UsuarioPlataforma usuario;
	
	@Transient
	private List<String> servicos;
	
	private String servicosCompac;
	
	private double valor;
	
	private int diariaOuMeia;
	
	//@OneToOne
   //private Servicos servicos;

	public Integer getIdUsuarioServico() {
		return IdUsuarioServico;
	}

	public void setIdUsuarioServico(Integer idUsuarioServico) {
		IdUsuarioServico = idUsuarioServico;
	}

	public UsuarioPlataforma getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPlataforma usuario) {
		this.usuario = usuario;
	}

	/*public Servicos getServicos() {
		return servicos;
	}

	public void setServicos(Servicos servicos) {
		this.servicos = servicos;
	}*/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getDiariaOuMeia() {
		return diariaOuMeia;
	}

	public void setDiariaOuMeia(int diariaOuMeia) {
		this.diariaOuMeia = diariaOuMeia;
	}

	public List<String> getServicos() {
		return servicos;
	}

	public void setServicos(List<String> servicos) {
		this.servicos = servicos;
	}

	public String getServicosCompac() {
		return servicosCompac;
	}

	public void setServicosCompac(String servicosCompac) {
		this.servicosCompac = servicosCompac;
	}

	



	
}
