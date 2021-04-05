package com.ufersa.webru.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Gestor extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String matricula;
	private String nome;
	
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
