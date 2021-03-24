package com.ufersa.webru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufersa.webru.model.ParametroValorMonetario;
import com.ufersa.webru.repositories.ParametroValorMonetarioRepository;

@Controller
public class ParametroValorMonetarioController {

	@Autowired
	private ParametroValorMonetarioRepository parametroValorMonetarioRepository;
	
	@RequestMapping(value="/cadastrarParametroValorMonetario", method=RequestMethod.GET)
	public String formularioCadastrarParametroValorMonetario() {
		return "parametro/cadastraParametroValorMonetario";
	}
	
	@RequestMapping(value="/cadastrarParametroValorMonetario", method=RequestMethod.POST)
	public String cadastrarParametro(ParametroValorMonetario parametroValorMonetario) {
		//System.out.println(parametroHora.getIdentificador());
		parametroValorMonetarioRepository.save(parametroValorMonetario);
		return "redirect:/cadastrarParametroValorMonetario";
	}
}
