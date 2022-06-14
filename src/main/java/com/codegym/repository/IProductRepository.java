package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<Product,Long> {
    Iterable<Product> findAllByNameContaining(String name);
    Iterable<Product> findAllByOrderByPrice();

    @Query(value = "select * from products order by id desc LIMIT 4", nativeQuery = true)
    Iterable<Product> getTop4();
}
