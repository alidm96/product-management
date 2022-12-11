package com.digipay.productmanagement.Repository;

import com.digipay.productmanagement.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}