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
    @GetMapping("/search/{name}")
    public ResponseEntity<Product> findCustomerByName(@PathVariable String  name) {
        List<Product> products = (List<Product>) productService.findByName(name);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product  product){
       return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(optionalProduct.get().getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteCustomer(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(optionalProduct.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sortByPrice")
    public ResponseEntity<Iterable<Product>> findAllByOrderByPrice() {
        Iterable<Product> products = productService.findAllByOrderByPrice();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/getTop4")
    public ResponseEntity<Iterable<Product>> getTop4() {
        Iterable<Product> products = productService.getTop4();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
