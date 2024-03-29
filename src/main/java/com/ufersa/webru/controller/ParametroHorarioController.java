package com.ufersa.webru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufersa.webru.model.ParametroHorario;
import com.ufersa.webru.repositories.ParametroHorarioRepository;

@Controller
public class ParametroHorarioController {
	
	@Autowired
	private ParametroHorarioRepository parametroHorarioRepository;

	@RequestMapping(value="/cadastrarParametroHorario", method=RequestMethod.GET)
	public String formularioCadastrarParametroHorario() {
		return "parametro/cadastraParametroHorario";
	}
	
	@RequestMapping(value="/cadastrarParametroHorario", method=RequestMethod.POST)
	public String cadastrarParametro(ParametroHorario parametroHora) {
		parametroHorarioRepository.save(parametroHora);
		return "redirect:/cadastrarParametroHorario";
	}
	
}