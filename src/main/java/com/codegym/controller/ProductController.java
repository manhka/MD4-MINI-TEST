package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("products")
public class ProductController {
    @Autowired
    IProductService productService;
    @GetMapping("")
    public ResponseEntity<Iterable<Product>> productList(){
        return new ResponseEntity(productService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<Product> findCustomerByName(@PathVariable String  name) {
        List<Product> products = (List<Product>) productService.findByName(name);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
