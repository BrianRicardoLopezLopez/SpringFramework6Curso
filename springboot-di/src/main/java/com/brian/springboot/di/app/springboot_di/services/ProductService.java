package com.brian.springboot.di.app.springboot_di.services;

import com.brian.springboot.di.app.springboot_di.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);
}
