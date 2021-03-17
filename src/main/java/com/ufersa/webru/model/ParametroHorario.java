package com.ufersa.webru.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;

@Entity
public class ParametroHorario extends Parametro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LocalTime horario;

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
}
