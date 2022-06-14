package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByOrderByPrice();

    Iterable<Product> getTop4();
}
