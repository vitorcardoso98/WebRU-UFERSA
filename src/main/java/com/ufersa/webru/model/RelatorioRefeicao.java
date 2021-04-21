package com.ufersa.webru.model;

import java.io.Serializable;

public class RelatorioRefeicao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private double valorTotalRefeicoesJantarIntegral;
	private double valorTotalRefeicoesAlmocoIntegral;
	private double valorTotalRefeicoesJantarParcial;
	private double valorTotalRefeicoesAlmocoParcial;
	
	private double valorTotal;

	public double getValorTotalRefeicoesJantarIntegral() {
		return valorTotalRefeicoesJantarIntegral;
	}

	public void setValorTotalRefeicoesJantarIntegral(double valorTotalRefeicoesJantarIntegral) {
		this.valorTotalRefeicoesJantarIntegral = this.valorTotalRefeicoesJantarIntegral + valorTotalRefeicoesJantarIntegral;
	}

	public double getValorTotalRefeicoesAlmocoIntegral() {
		return valorTotalRefeicoesAlmocoIntegral;
	}

	public void setValorTotalRefeicoesAlmocoIntegral(double valorTotalRefeicoesAlmocoIntegral) {
		this.valorTotalRefeicoesAlmocoIntegral = this.valorTotalRefeicoesAlmocoIntegral + valorTotalRefeicoesAlmocoIntegral;
	}

	public double getValorTotalRefeicoesJantarParcial() {
		return valorTotalRefeicoesJantarParcial;
	}

	public void setValorTotalRefeicoesJantarParcial(double valorTotalRefeicoesJantarParcial) {
		this.valorTotalRefeicoesJantarParcial = this.valorTotalRefeicoesJantarParcial + valorTotalRefeicoesJantarParcial;
	}

	public double getValorTotalRefeicoesAlmocoParcial() {
		return valorTotalRefeicoesAlmocoParcial;
	}

	public void setValorTotalRefeicoesAlmocoParcial(double valorTotalRefeicoesAlmocoParcial) {
		this.valorTotalRefeicoesAlmocoParcial = this.valorTotalRefeicoesAlmocoParcial + valorTotalRefeicoesAlmocoParcial;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
