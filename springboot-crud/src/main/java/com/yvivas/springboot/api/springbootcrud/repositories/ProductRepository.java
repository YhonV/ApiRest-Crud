package com.yvivas.springboot.api.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.yvivas.springboot.api.springbootcrud.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    boolean existsBySku(String sku);
}
