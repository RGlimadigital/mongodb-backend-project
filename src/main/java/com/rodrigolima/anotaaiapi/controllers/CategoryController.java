package com.rodrigolima.anotaaiapi.controllers;

import com.rodrigolima.anotaaiapi.domain.category.Category;
import com.rodrigolima.anotaaiapi.domain.category.CategoryDTO;
import com.rodrigolima.anotaaiapi.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO categoryData){
        return ResponseEntity.ok().body(service.insert(categoryData));
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable String id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryData){
        return ResponseEntity.ok().body(service.updateCategory(id, categoryData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")  String id){
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
