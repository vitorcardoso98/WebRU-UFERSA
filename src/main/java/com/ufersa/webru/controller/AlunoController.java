package com.ufersa.webru.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufersa.webru.model.Aluno;
import com.ufersa.webru.repositories.AlunoRepository;

@Controller
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@RequestMapping(value="/listarAlunos", method=RequestMethod.GET)
	public ModelAndView listarAlunos() {
		ModelAndView modeloAluno = new ModelAndView("aluno/listaAlunos");
		Iterable<Aluno> alunos = alunoRepository.findAll();
		modeloAluno.addObject("alunos", alunos);
		return modeloAluno;
	}
	
	@RequestMapping(value="/cadastrarAluno", method=RequestMethod.POST)
	public String cadastrarAlunos() {

		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("my-json-server.typicode.com")
				.path("vitorcardoso98/fakeapi-ufersaru/alunos")
				.build();

		ResponseEntity<Aluno[]> entityAluno = template.getForEntity(uri.toString(), Aluno[].class);
		
		ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
		
		for(Aluno aluno: entityAluno.getBody()) {
			listaAlunos.add(aluno);
		}
		
		alunoRepository.saveAll(listaAlunos);
		
		return "redirect:/listarAlunos";
	}

}
