package com.ufersa.webru.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Atendente extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
