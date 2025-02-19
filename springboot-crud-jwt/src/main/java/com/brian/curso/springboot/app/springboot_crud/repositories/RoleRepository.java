package com.brian.curso.springboot.app.springboot_crud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.brian.curso.springboot.app.springboot_crud.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{

    // un metodo con una consulta personalizada
    Optional<Role> findByName(String name);

}
