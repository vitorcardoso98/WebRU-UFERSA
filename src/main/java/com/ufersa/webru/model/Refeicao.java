package com.ufersa.webru.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ufersa.webru.enums.TipoRefeicaoEnum;
import com.ufersa.webru.enums.TipoSubsidioEnum;

@Entity
public class Refeicao implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long idRefeicao;
    private double valor;
    private LocalDate dataRefeicao;
    
    @Enumerated(EnumType.STRING)
	private TipoRefeicaoEnum tipoRefeicao;
	
    @Enumerated(EnumType.STRING)
	private TipoSubsidioEnum tipoSubsidio;
	
    @OneToOne
    @JoinColumn(name = "chave_do_aluno")
	private Aluno aluno;
	
	public long getIdRefeicao() {
		return idRefeicao;
	}
	public void setIdRefeicao(long idRefeicao) {
		this.idRefeicao = idRefeicao;
	}
	public TipoRefeicaoEnum getTipoRefeicao() {
		return tipoRefeicao;
	}
	public void setTipoRefeicao(TipoRefeicaoEnum tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}
	public TipoSubsidioEnum getTipoSubsidio() {
		return tipoSubsidio;
	}
	public void setTipoSubsidio(TipoSubsidioEnum tipoSubsidio) {
		this.tipoSubsidio = tipoSubsidio;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public LocalDate getDataRefeicao() {
		return dataRefeicao;
	}
	public void setDataRefeicao(LocalDate dataRefeicao) {
		this.dataRefeicao = dataRefeicao;
	}

}
