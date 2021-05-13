package com.ufersa.webru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ufersa.webru.model.Aluno;
import com.ufersa.webru.model.Avaliacao;
import com.ufersa.webru.repositories.AlunoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			Aluno aluno = alunoRepository.findByMatricula(username);
			avaliacao.setAluno(aluno);
			return avaliacao;
		} else {
			String username = principal.toString();
			return null;
		}
	}

}
