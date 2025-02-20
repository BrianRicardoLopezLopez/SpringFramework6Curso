package com.brian.curso.springboot.backend.backend_products.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.brian.curso.springboot.backend.backend_products.entities.Product;

//React-App and Angular-App
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200/"})

@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long>{

}
