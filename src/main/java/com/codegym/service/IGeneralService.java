package com.codegym.service;

import com.codegym.model.Product;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();
    Iterable<T> findByName(String name);

    Optional<T> findById(Long id);
    T save(T t);
    void remove(Long id);

}
