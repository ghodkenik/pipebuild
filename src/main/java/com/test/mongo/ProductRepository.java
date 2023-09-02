package com.test.mongo;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
    
    @Override
    public void delete(Product product);
    
}