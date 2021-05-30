package com.ufersa.webru.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ufersa.webru.model.Refeicao;
import com.ufersa.webru.model.RelatorioRefeicao;
import com.ufersa.webru.repositories.RefeicaoRepository;
import com.ufersa.webru.services.RefeicaoService;

@Controller
public class RefeicaoController {
	
	@Autowired
	private RefeicaoRepository refeicaoRepository;
	
	@Autowired
	private RefeicaoService refeicaoService;
	
	@RequestMapping(value="/cadastrarRefeicao", method=RequestMethod.GET)
	public String formularioCadastrarRefeicao() {
		return "refeicao/cadastraRefeicao";
	}
	
	@RequestMapping(value="/cadastrarRefeicao", method=RequestMethod.POST)
	public ModelAndView cadastrarRefeicao(HttpServletRequest request) {
		Refeicao refeicao = refeicaoService.cadastrarRefeicao(request.getParameter("matricula"));
		System.out.println();
		if(refeicao==null) {
			ModelAndView modeloRefeicao = new ModelAndView("erroRefeicao");
			return modeloRefeicao;
		}else {
			refeicaoRepository.save(refeicao);
			ModelAndView modeloRefeicao = new ModelAndView("refeicao/informacoesRefeicao");
			modeloRefeicao.addObject("refeicao", refeicao);
			return modeloRefeicao;
		}
	}
	
	@RequestMapping(value="/listarRefeicoes", method=RequestMethod.GET)
	public ModelAndView listarRefeicoesCadastradas() {
		ModelAndView modeloRefeicao = new ModelAndView("refeicao/listaRefeicoes");
		Iterable<Refeicao> refeicoes = refeicaoService.listarRefeicoes();
		modeloRefeicao.addObject("refeicoes", refeicoes);
		return modeloRefeicao;
	}
	
	@RequestMapping(value="/escolheMesRelatorio", method=RequestMethod.GET)
	public ModelAndView escolheMesRelatorio() {
		ModelAndView modeloRelatorioRefeicao = new ModelAndView("refeicao/escolheMesRelatorio");
		return modeloRelatorioRefeicao;
	}
	
	@RequestMapping(value="/relatorioCompletoRefeicoes", method=RequestMethod.GET)
	public ModelAndView relatorioCompletoRefeicoes(@RequestParam String mes) { 
		ModelAndView relatorio = new ModelAndView("refeicao/relatorioCompletoRefeicoes");
		
		List<Refeicao> refeicoes = refeicaoService.getRelatorioRefeicaoMes(mes);
		RelatorioRefeicao valores = refeicaoService.getValoresRelatorio(mes);
		
		relatorio.addObject("refeicoes", refeicoes);
		relatorio.addObject("valores", valores);
		
		return relatorio;
	}
}