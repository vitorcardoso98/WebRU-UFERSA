package com.ufersa.webru.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufersa.webru.model.Aluno;
import com.ufersa.webru.model.Role;
import com.ufersa.webru.repositories.AlunoRepository;
import com.ufersa.webru.repositories.RoleRepository;

@Controller
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping(value = "/listarAlunos", method = RequestMethod.GET)
	public ModelAndView listarAlunos() {
		ModelAndView modeloAluno = new ModelAndView("aluno/listaAlunos");
		Iterable<Aluno> alunos = alunoRepository.findAll();
		modeloAluno.addObject("alunos", alunos);
		return modeloAluno;
	}
	
	@RequestMapping(value = "/relatorioAlunos", method = RequestMethod.GET)
	public ModelAndView relatorioAlunos() {
		ModelAndView modeloAluno = new ModelAndView("aluno/relatorioAlunos");
		Iterable<Aluno> alunos = alunoRepository.findAll();
		modeloAluno.addObject("alunos", alunos);
		return modeloAluno;
	}

	@RequestMapping(value = "/cadastrarAluno", method = RequestMethod.POST)
	public String cadastrarAlunos() {

		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https").host("my-json-server.typicode.com")
				.path("vitorcardoso98/fakeapi-ufersaru/alunos").build();

		ResponseEntity<Aluno[]> entityAluno = template.getForEntity(uri.toString(), Aluno[].class);

		ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
		ArrayList<Role> roles = new ArrayList<Role>();

		Role role = new Role();
		role.setNomeRole("ROLE_ALUNO");
		roles.add(role);
		roleRepository.save(role);

		for (Aluno aluno : entityAluno.getBody()) {
			aluno.setLogin(aluno.getMatricula());
			aluno.setSenha(new BCryptPasswordEncoder().encode(aluno.getMatricula()));
			aluno.setRoles(roles);
			listaAlunos.add(aluno);
		}

		alunoRepository.saveAll(listaAlunos);

		return "redirect:/listarAlunos";
	}

}
