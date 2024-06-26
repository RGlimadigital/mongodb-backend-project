package com.rodrigolima.anotaaiapi.services;

import com.rodrigolima.anotaaiapi.domain.category.Category;
import com.rodrigolima.anotaaiapi.domain.products.Product;
import com.rodrigolima.anotaaiapi.domain.products.ProductDTO;
import com.rodrigolima.anotaaiapi.domain.products.exceptions.ProductNotFoundException;
import com.rodrigolima.anotaaiapi.repositories.ProductRepository;
import com.rodrigolima.anotaaiapi.services.aws.AwsSnsService;
import com.rodrigolima.anotaaiapi.services.aws.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AwsSnsService snsService;

    public Product insertProduct(ProductDTO productData){
        Category category = categoryService.findById(productData.categoryId());

        Product newProduct = new Product(productData);
        newProduct.setCategory(category);

        snsService.publish(new MessageDTO(newProduct.getOwnerId()));

        return repository.save(newProduct);
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(String id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not Found"));
    }

    public Product updateProduct(String id, ProductDTO productDTO){
        Product productToUpdate = repository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(!productDTO.title().isEmpty())
            productToUpdate.setTitle(productDTO.title());
        if(!productDTO.description().isEmpty())
            productToUpdate.setDescription(productDTO.description());
        if((productDTO.price() != null))
            productToUpdate.setPrice(productDTO.price());
        if(!productDTO.categoryId().isEmpty()){
            Category category = categoryService.findById(productDTO.categoryId());
            productToUpdate.setCategory(category);
        }

        snsService.publish(new MessageDTO(productToUpdate.getOwnerId()));

        return repository.save(productToUpdate);

    }


    public void deleteProduct(String id){
        Product productToDelete = repository.findById(id).orElseThrow(ProductNotFoundException::new);
        repository.delete(productToDelete);
    }

}
