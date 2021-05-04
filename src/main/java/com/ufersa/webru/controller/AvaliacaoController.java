package com.ufersa.webru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufersa.webru.model.Avaliacao;

@Controller
public class AvaliacaoController {

	@RequestMapping(value="/avaliar", method=RequestMethod.GET)
	public String avaliar() {
		return "avaliacao/avaliar";
	}
	
	@RequestMapping(value="/avaliar", method=RequestMethod.POST)
	public String cadastrarAvaliacao(Avaliacao avaliacao) {
		
		System.out.println(avaliacao.getOpcao());
		
		return "avaliacao/avaliar";
	}
}
