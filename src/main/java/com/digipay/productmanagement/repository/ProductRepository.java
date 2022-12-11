package com.digipay.productmanagement.repository;

import com.digipay.productmanagement.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}