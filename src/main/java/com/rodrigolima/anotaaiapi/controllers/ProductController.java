package com.rodrigolima.anotaaiapi.controllers;

import com.rodrigolima.anotaaiapi.domain.products.Product;
import com.rodrigolima.anotaaiapi.domain.products.ProductDTO;
import com.rodrigolima.anotaaiapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productData){
        return ResponseEntity.ok().body(service.insertProduct(productData));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO productData){
        return ResponseEntity.ok().body(service.updateProduct(id, productData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
