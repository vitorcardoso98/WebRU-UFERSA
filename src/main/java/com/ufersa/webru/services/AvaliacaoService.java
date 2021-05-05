package com.ufersa.webru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufersa.webru.model.Avaliacao;
import com.ufersa.webru.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao) {
		Avaliacao ava = avaliacaoRepository.save(avaliacao);
		return ava;
	}
	
}
