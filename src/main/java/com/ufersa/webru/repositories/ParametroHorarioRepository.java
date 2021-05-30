package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.ParametroHorario;

@Repository
public interface ParametroHorarioRepository extends CrudRepository<ParametroHorario, Long>{

	ParametroHorario findByIdentificador(String identificador);
}
