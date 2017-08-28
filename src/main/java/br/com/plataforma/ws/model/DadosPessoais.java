package br.com.plataforma.ws.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DadosPessoais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer IdDadosPessoais;
	
	private String nome;
	private String email;
	private String cel;
	
	private String cpf;
	
	private String ruaOuAv;
	
	private String bairro;
	
	private int numero;
	
	private String cidade;
	
	private String uf;
	
	private double Logitude;
	private double Latitude;
	
	private String url;
	
	@OneToOne
	private UsuarioPlataforma usuario;
	
	public Integer getIdDadosPessoais() {
		return IdDadosPessoais;
	}
	public void setIdDadosPessoais(Integer idDadosPessoais) {
		IdDadosPessoais = idDadosPessoais;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	public double getLogitude() {
		return Logitude;
	}
	public void setLogitude(double logitude) {
		Logitude = logitude;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public UsuarioPlataforma getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioPlataforma usuario) {
		this.usuario = usuario;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRuaOuAv() {
		return ruaOuAv;
	}
	public void setRuaOuAv(String ruaOuAv) {
		this.ruaOuAv = ruaOuAv;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	


}
