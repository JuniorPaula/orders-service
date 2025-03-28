package com.juniorpaula.webserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorpaula.webserver.dto.ProductDTO;
import com.juniorpaula.webserver.entities.Category;
import com.juniorpaula.webserver.entities.Product;
import com.juniorpaula.webserver.repositories.ProductRepository;

@Service
public class ProductService {
  
  @Autowired
  private ProductRepository repository;

  @Autowired
  private CategoryService categoryService;

  public List<Product> findAll() {
    return repository.findAll();
  }

  public Product findById(Long id) {
    Optional<Product> obj = repository.findById(id);
    return obj.get();
  }

  public Product insert(ProductDTO obj) {
    Product product = new Product(null, obj.name(), obj.description(), obj.price(), obj.imageUrl());

    for (Long id : obj.categoriesIds()) {
      Category category = categoryService.findById(id);
      product.getCategories().add(category);
    }

    return repository.save(product);
  }
}
