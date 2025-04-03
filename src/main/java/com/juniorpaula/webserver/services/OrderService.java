package com.juniorpaula.webserver.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorpaula.webserver.entities.Order;
import com.juniorpaula.webserver.entities.OrderItem;
import com.juniorpaula.webserver.entities.Product;
import com.juniorpaula.webserver.entities.User;
import com.juniorpaula.webserver.entities.enums.OrderStatus;
import com.juniorpaula.webserver.repositories.OrderItemRepository;
import com.juniorpaula.webserver.repositories.OrderRepository;
import com.juniorpaula.webserver.repositories.ProductRepository;
import com.juniorpaula.webserver.security.SecurityUtils;
import com.juniorpaula.webserver.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
  
  @Autowired
  private OrderRepository repository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  public List<Order> findAll() {
    return repository.findAll();
  }

  public Order findById(Long id) {
    Optional<Order> obj = repository.findById(id);
    return obj.get();
  }

  public Order insert(Long productId, Integer quantity) {
    User user = SecurityUtils.getCurrentUser();
    Order order = new Order(null, Instant.now(), OrderStatus.WAITING_PAYMENT, user);

    Optional<Product> objProduct = productRepository.findById(productId);
    if (!objProduct.isPresent()) {
      throw new ResourceNotFoundException(productId);
    }
    Product product = objProduct.get();

    OrderItem orderItem = new OrderItem(order, product, quantity, product.getPrice());

    order.getItems().add(orderItem);
    repository.save(order);

    orderItemRepository.save(orderItem);

    return order;
  }
}
