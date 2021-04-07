package com.ufersa.webru.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufersa.webru.model.Atendente;
import com.ufersa.webru.model.Role;
import com.ufersa.webru.repositories.AtendenteRepository;
import com.ufersa.webru.repositories.RoleRepository;

@Controller
public class AtendenteController {

	@Autowired
	private AtendenteRepository atendenteRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value = "/cadastrarAtendente", method = RequestMethod.GET)
	public String formularioCadastrarAtendente() {
		return "atendente/cadastraAtendente";
	}

	@RequestMapping(value = "/cadastrarAtendente", method = RequestMethod.POST)
	public String CadastrarAtendente(Atendente atendente) {

		ArrayList<Role> roles = new ArrayList<Role>();

		Role role = new Role();
		role.setNomeRole("ROLE_ATENDENTE");
		roles.add(role);

		roleRepository.save(role);
		
		atendente.setSenha(new BCryptPasswordEncoder().encode(atendente.getSenha()));
		atendente.setRoles(roles);
		
		atendenteRepository.save(atendente);
		
		return "redirect:/cadastrarAtendente";
	}
}
