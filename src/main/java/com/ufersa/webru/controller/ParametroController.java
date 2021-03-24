package com.ufersa.webru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ufersa.webru.model.ParametroHorario;
import com.ufersa.webru.model.ParametroValorMonetario;
import com.ufersa.webru.repositories.ParametroHorarioRepository;
import com.ufersa.webru.repositories.ParametroValorMonetarioRepository;

@Controller
public class ParametroController {

	@Autowired
	private ParametroHorarioRepository parametroHorarioRepository;
	
	@Autowired
	private ParametroValorMonetarioRepository parametroValorMonetarioRepository;
	
	@RequestMapping(value="/listarParametros", method=RequestMethod.GET)
	public ModelAndView listarParametros() {
		ModelAndView modeloParametro = new ModelAndView("parametro/listaParametros");
		Iterable<ParametroHorario> parametrosHorarios = parametroHorarioRepository.findAll();
		Iterable<ParametroValorMonetario> parametrosMonetarios = parametroValorMonetarioRepository.findAll();
		modeloParametro.addObject("parametrosHorarios", parametrosHorarios);
		modeloParametro.addObject("parametrosMonetarios", parametrosMonetarios);
		return modeloParametro;
	}
}
