package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ufersa.webru.model.Parametro;

public interface ParametroRepository extends CrudRepository<Parametro, Long>{
	
	Parametro findByCodigo(long codigo);
	
}