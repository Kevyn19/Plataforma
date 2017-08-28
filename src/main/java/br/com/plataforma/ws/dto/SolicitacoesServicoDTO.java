package br.com.plataforma.ws.dto;

import java.util.Date;

public class SolicitacoesServicoDTO {
	
	private Integer id;
	
	//Nome do solicitante
	private String nome;
	
	private String data;
	
	private int manhaOuTardeDiaria;
	
	private int tamanhoCasa;
	
	private int nComodos;
	
	private int distancia;
	
	
	

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
