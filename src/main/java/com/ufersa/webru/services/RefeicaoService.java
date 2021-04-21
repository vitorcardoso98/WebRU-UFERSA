package com.ufersa.webru.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufersa.webru.enums.TipoRefeicaoEnum;
import com.ufersa.webru.enums.TipoSubsidioEnum;
import com.ufersa.webru.model.Refeicao;
import com.ufersa.webru.model.RelatorioRefeicao;
import com.ufersa.webru.repositories.RefeicaoRepository;

@Service
public class RefeicaoService {
	
	@Autowired
	private RefeicaoRepository refeicaoRepository;
	
	public List<Refeicao> getRelatorioRefeicaoMes(String mes){
		List<Refeicao> refeicoes = refeicaoRepository.getRelatorio(mes);
		return refeicoes;
	}
	
	public RelatorioRefeicao getValoresRelatorio(String mes) {
		
		RelatorioRefeicao relatorioRefeicao = new RelatorioRefeicao();
		
		List<Refeicao> refeicoes = refeicaoRepository.getRelatorio(mes);
		
		for(Refeicao refeicao: refeicoes) {
			if(refeicao.getTipoRefeicao() == TipoRefeicaoEnum.ALMOCO) {
				if(refeicao.getTipoSubsidio() == TipoSubsidioEnum.INTEGRAL) {
					relatorioRefeicao.setValorTotalRefeicoesAlmocoIntegral(refeicao.getValor());
				}else {
					relatorioRefeicao.setValorTotalRefeicoesAlmocoParcial(refeicao.getValor());
				}
			}else {
				if((refeicao.getTipoSubsidio() == TipoSubsidioEnum.INTEGRAL)) {
					relatorioRefeicao.setValorTotalRefeicoesJantarIntegral(refeicao.getValor());
				}else {
					relatorioRefeicao.setValorTotalRefeicoesAlmocoParcial(refeicao.getValor());
				}
			}
		}
		
		return relatorioRefeicao;
	}

}
