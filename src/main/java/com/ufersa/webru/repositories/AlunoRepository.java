package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	Aluno findByMatricula(String matricula);
	
}
