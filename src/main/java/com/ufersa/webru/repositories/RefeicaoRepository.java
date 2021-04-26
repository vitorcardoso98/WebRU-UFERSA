package com.ufersa.webru.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufersa.webru.model.Refeicao;

public interface RefeicaoRepository extends CrudRepository<Refeicao, Long> {

	List<Refeicao> findAll();

	@Query(value = "SELECT * FROM refeicao WHERE month(data_refeicao) = ?1", nativeQuery = true)
	List<Refeicao> getRelatorio(String mes);

}
