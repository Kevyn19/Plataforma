package br.com.plataforma.ws.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TransacoConsolidada implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer IdTransacoConsolidada;
	
	@OneToOne
	private SolicitacaoServico solicitacao;
	
	private double valorTransacao;
	
	private Date data;
	
	private Timestamp hora;


	public Integer getIdTransacoConsolidada() {
		return IdTransacoConsolidada;
	}

	public void setIdTransacoConsolidada(Integer idTransacoConsolidada) {
		IdTransacoConsolidada = idTransacoConsolidada;
	}

	public SolicitacaoServico getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoServico solicitacao) {
		this.solicitacao = solicitacao;
	}

	public double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Timestamp getHora() {
		return hora;
	}

	public void setHora(Timestamp hora) {
		this.hora = hora;
	}



}
