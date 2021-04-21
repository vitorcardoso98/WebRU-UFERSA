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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((dataRefeicao == null) ? 0 : dataRefeicao.hashCode());
		result = prime * result + (int) (idRefeicao ^ (idRefeicao >>> 32));
		result = prime * result + ((tipoRefeicao == null) ? 0 : tipoRefeicao.hashCode());
		result = prime * result + ((tipoSubsidio == null) ? 0 : tipoSubsidio.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Refeicao other = (Refeicao) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (dataRefeicao == null) {
			if (other.dataRefeicao != null)
				return false;
		} else if (!dataRefeicao.equals(other.dataRefeicao))
			return false;
		if (idRefeicao != other.idRefeicao)
			return false;
		if (tipoRefeicao != other.tipoRefeicao)
			return false;
		if (tipoSubsidio != other.tipoSubsidio)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Refeicao [idRefeicao=" + idRefeicao + ", valor=" + valor + ", dataRefeicao=" + dataRefeicao
				+ ", tipoRefeicao=" + tipoRefeicao + ", tipoSubsidio=" + tipoSubsidio + ", aluno=" + aluno + "]";
	}
	
	

}
