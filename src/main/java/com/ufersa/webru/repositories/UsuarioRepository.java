package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findByLogin(String login);
	
}
