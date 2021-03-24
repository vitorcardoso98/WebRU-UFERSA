package com.ufersa.webru.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class ParametroValorMonetario extends Parametro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private double valorMonetario;

	public double getValorMonetario() {
		return valorMonetario;
	}

	public void setValorMonetario(double valorMonetario) {
		this.valorMonetario = valorMonetario;
	}
	
	
	
}
