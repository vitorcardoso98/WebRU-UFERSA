package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.ParametroValorMonetario;

@Repository
public interface ParametroValorMonetarioRepository extends CrudRepository<ParametroValorMonetario, Long>{

	ParametroValorMonetario findByIdentificador(String identificador);
}
