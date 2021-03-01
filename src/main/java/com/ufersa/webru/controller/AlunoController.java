package com.ufersa.webru.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufersa.webru.model.Aluno;

@Controller
public class AlunoController {

	
	@RequestMapping("/aluno/cadastrar")
	public ModelAndView cadastrarAlunos() {

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

		ModelAndView mv = new ModelAndView("aluno/cadastrarAlunos");
		mv.addObject("alunos", listaAlunos);
		return mv;
	}

}
