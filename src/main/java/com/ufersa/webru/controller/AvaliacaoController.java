package com.ufersa.webru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ufersa.webru.model.Avaliacao;
import com.ufersa.webru.repositories.AvaliacaoRepository;
import com.ufersa.webru.services.AvaliacaoService;

@Controller
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@RequestMapping(value="/avaliar", method=RequestMethod.GET)
	public String avaliar() {
		return "avaliacao/avaliar";
	}
	
	@RequestMapping(value="/avaliar", method=RequestMethod.POST)
	public String cadastrarAvaliacao(Avaliacao avaliacao) {
		Avaliacao ava = avaliacaoService.cadastrarAvaliacao(avaliacao);
		avaliacaoRepository.save(ava);
		return "avaliacao/avaliar";
	}
	
	@RequestMapping(value="/listarAvaliacoes", method=RequestMethod.GET)
	public ModelAndView listarAvaliacoesCadastradas() {
		ModelAndView modeloAvaliacao = new ModelAndView("avaliacao/listaAvaliacoes");
		Iterable<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
		modeloAvaliacao.addObject("avaliacoes", avaliacoes);
		return modeloAvaliacao;
	}
}
