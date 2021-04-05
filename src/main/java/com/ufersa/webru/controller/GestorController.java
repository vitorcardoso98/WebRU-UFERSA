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

import com.ufersa.webru.model.Gestor;
import com.ufersa.webru.model.Role;
import com.ufersa.webru.repositories.GestorRepository;

@Controller
public class GestorController {

	@Autowired
	private GestorRepository gestorRepository;
	
	@RequestMapping(value="/listarGestores", method=RequestMethod.GET)
	public ModelAndView listarGestores() {
		ModelAndView modeloGestor = new ModelAndView("gestor/listaGestores");
		Iterable<Gestor> gestores = gestorRepository.findAll();
		modeloGestor.addObject("gestores", gestores);
		return modeloGestor;
	}
	
	@RequestMapping(value="/importarGestores", method=RequestMethod.POST)
	public String importarGestores() {

		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("my-json-server.typicode.com")
				.path("vitorcardoso98/fakeapi-ufersaru/gestores")
				.build();

		ResponseEntity<Gestor[]> entityGestor = template.getForEntity(uri.toString(), Gestor[].class);
		
		ArrayList<Gestor> listaGestores = new ArrayList<Gestor>();
		ArrayList<Role> roles = new ArrayList<Role>();
		
		Role role = new Role();
		role.setNomeRole("ROLE_ADMIN_UFERSA");
		roles.add(role);
		
		for(Gestor gestor: entityGestor.getBody()) {
			gestor.setLogin(gestor.getMatricula());
			gestor.setSenha(new BCryptPasswordEncoder().encode(gestor.getMatricula()));
			gestor.setRoles(roles);
			listaGestores.add(gestor);
		}
		
		
		gestorRepository.saveAll(listaGestores);
		
		return "redirect:/listarGestores";
	}
}
