package com.ufersa.webru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufersa.webru.model.Avaliacao;
import com.ufersa.webru.services.AvaliacaoService;

@Controller
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@RequestMapping(value="/avaliar", method=RequestMethod.GET)
	public String avaliar() {
		return "avaliacao/avaliar";
	}
	
	@RequestMapping(value="/avaliar", method=RequestMethod.POST)
	public String cadastrarAvaliacao(Avaliacao avaliacao) {
		
		avaliacaoService.cadastrarAvaliacao(avaliacao);
		
		return "avaliacao/avaliar";
	}
}
