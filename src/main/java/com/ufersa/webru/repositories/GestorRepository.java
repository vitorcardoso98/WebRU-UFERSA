package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.Gestor;

@Repository
public interface GestorRepository extends CrudRepository<Gestor, Long>{

}
