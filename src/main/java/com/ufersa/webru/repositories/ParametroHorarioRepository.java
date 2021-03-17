package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ufersa.webru.model.ParametroHorario;

public interface ParametroHorarioRepository extends CrudRepository<ParametroHorario, Long>{

	ParametroHorario findByIdentificador(String identificador);
}
