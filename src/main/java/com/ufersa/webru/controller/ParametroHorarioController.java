package com.ufersa.webru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ufersa.webru.model.ParametroHorario;
import com.ufersa.webru.repositories.ParametroHorarioRepository;

@Controller
public class ParametroHorarioController {
	
	@Autowired
	private ParametroHorarioRepository parametroHorarioRepository;

	@RequestMapping(value="/cadastrarParametro", method=RequestMethod.GET)
	public String formularioCadastrarParametro() {
		return "parametro/cadastraParametro";
	}
	
	@RequestMapping(value="/cadastrarParametro", method=RequestMethod.POST)
	public String cadastrarParametro(ParametroHorario parametroHora) {
		System.out.println(parametroHora.getIdentificador());
		parametroHorarioRepository.save(parametroHora);
		return "redirect:/cadastrarParametro";
	}
	
	@RequestMapping(value="/listarParametros", method=RequestMethod.GET)
	public ModelAndView listarParametros() {
		ModelAndView modeloParametro = new ModelAndView("parametro/listaParametros");
		Iterable<ParametroHorario> parametros = parametroHorarioRepository.findAll();
		modeloParametro.addObject("parametros", parametros);
		return modeloParametro;
	}
	
	
}