package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends CrudRepository<Category,Long> {
    Iterable<Category> findAllByNameContaining(String name);
}
