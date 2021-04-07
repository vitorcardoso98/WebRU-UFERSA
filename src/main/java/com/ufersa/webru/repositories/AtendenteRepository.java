package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.Atendente;

@Repository
public interface AtendenteRepository extends CrudRepository<Atendente, String>{

}
