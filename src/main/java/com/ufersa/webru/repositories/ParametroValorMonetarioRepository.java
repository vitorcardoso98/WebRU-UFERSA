package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ufersa.webru.model.ParametroValorMonetario;

public interface ParametroValorMonetarioRepository extends CrudRepository<ParametroValorMonetario, Long>{

	ParametroValorMonetario findByIdentificador(String identificador);
}
