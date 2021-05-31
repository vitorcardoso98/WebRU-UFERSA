package com.ufersa.webru.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.Refeicao;

@Repository
public interface RefeicaoRepository extends CrudRepository<Refeicao, Long> {

	@Query(value = "SELECT * from refeicao where DATE_PART('MONTH', data_refeicao) = ?1", nativeQuery = true)
	List<Refeicao> getRelatorio(double mes);

}
