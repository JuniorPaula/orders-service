package com.juniorpaula.webserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorpaula.webserver.dto.ProductDTO;
import com.juniorpaula.webserver.entities.Category;
import com.juniorpaula.webserver.entities.Product;
import com.juniorpaula.webserver.repositories.ProductRepository;
import com.juniorpaula.webserver.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public Product insert(ProductDTO obj) {
    Product product = new Product(null, obj.name(), obj.description(), obj.price(), obj.imageUrl());

    for (Long id : obj.categoriesIds()) {
      Category category = categoryService.findById(id);
      product.getCategories().add(category);
    }

    return repository.save(product);
  }

  public Product update(Long id, ProductDTO obj) {
    try {

      Product entity = repository.getReferenceById(id);
      updateData(entity, obj);
      return repository.save(entity);
    }
    catch(EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  private void updateData(Product entity, ProductDTO obj) {
    entity.setName(obj.name() != null ? obj.name() : entity.getName());
    entity.setDescription(obj.description() != null ? obj.description() : entity.getDescription());
    entity.setPrice(obj.price());
    entity.setImgUrl(obj.imageUrl() != null ? obj.imageUrl() : entity.getImgUrl());

    entity.getCategories().clear();
    for (Long id : obj.categoriesIds()) {
      Category category = categoryService.findById(id);
      entity.getCategories().add(category);
    }
  }

  public void delete(Long id) {
    findById(id);
    repository.deleteById(id);
  }
}
