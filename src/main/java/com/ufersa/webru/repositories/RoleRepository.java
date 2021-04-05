package com.ufersa.webru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufersa.webru.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>{

}
