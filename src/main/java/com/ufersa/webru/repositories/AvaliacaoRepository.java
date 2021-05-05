package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long>{

}
