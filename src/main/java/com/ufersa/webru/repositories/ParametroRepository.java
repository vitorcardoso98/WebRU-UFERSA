package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.Parametro;

@Repository
public interface ParametroRepository extends CrudRepository<Parametro, Long>{
	
	Parametro findByCodigo(long codigo);

}
