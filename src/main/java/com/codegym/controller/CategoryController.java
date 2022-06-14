package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.repository.ICategoryRepository;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<Category> findAllCategory(){
        return new ResponseEntity(categoryService.findAll(), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@RequestBody Category category){
        Optional<Category> categoryOptional=categoryService.findById(id);
        if (!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(categoryOptional.get().getId());
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        Optional<Category> categoryOptional=categoryService.findById(id);
        if (!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<Category> findCategoryByName(@PathVariable String  name) {
        List<Category> categories = (List<Category>) categoryService.findByName(name);
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(categories, HttpStatus.OK);
    }
}
