package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ufersa.webru.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	Aluno findByMatricula(String matricula);
	
}
