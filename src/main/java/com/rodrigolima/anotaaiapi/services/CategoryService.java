package com.rodrigolima.anotaaiapi.services;

import com.rodrigolima.anotaaiapi.domain.category.Category;
import com.rodrigolima.anotaaiapi.domain.category.CategoryDTO;
import com.rodrigolima.anotaaiapi.domain.category.exceptions.CategoryNotFoundException;
import com.rodrigolima.anotaaiapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category insert(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);
        return repository.save(newCategory);
    }

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(String id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Element not Found"));
    }

    public Category updateCategory(String id, CategoryDTO categoryDTO){
        Category categoryToUpdate = repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryDTO.title().isEmpty())
            categoryToUpdate.setTitle(categoryDTO.title());
        if(!categoryDTO.description().isEmpty())
            categoryToUpdate.setDescription(categoryDTO.description());

        return repository.save(categoryToUpdate);
    }

    public void deleteCategory(String id){
        repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        repository.deleteById(id);
    }
}
