package com.ufersa.webru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ufersa.webru.model.Parametro;
import com.ufersa.webru.repositories.ParametroRepository;

@Controller
public class ParametroController {
	
	@Autowired
	private ParametroRepository parametroRepository;

	@RequestMapping(value="/cadastrarParametro", method=RequestMethod.GET)
	public String formularioCadastrarParametro() {
		return "parametro/cadastraParametro";
	}
	
	@RequestMapping(value="/cadastrarParametro", method=RequestMethod.POST)
	public String cadastrarParametro(Parametro parametro) {
		parametroRepository.save(parametro);
		return "redirect:/cadastrarParametro";
	}
	
	@RequestMapping(value="/listarParametros", method=RequestMethod.GET)
	public ModelAndView listarParametros() {
		ModelAndView modeloParametro = new ModelAndView("parametro/listaParametros");
		Iterable<Parametro> parametros = parametroRepository.findAll();
		modeloParametro.addObject("parametros", parametros);
		return modeloParametro;
	}
	
}