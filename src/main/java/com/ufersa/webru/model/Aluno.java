package com.ufersa.webru.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Aluno extends Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String matricula;
	String nome;
	String tipo;
	String status;
	
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", tipo=" + tipo + "]";
	}
	
	
	
}