package com.juniorpaula.webserver.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorpaula.webserver.dto.RequestOrderDTO;
import com.juniorpaula.webserver.entities.Client;
import com.juniorpaula.webserver.entities.Order;
import com.juniorpaula.webserver.entities.OrderItem;
import com.juniorpaula.webserver.entities.Product;
import com.juniorpaula.webserver.entities.enums.OrderStatus;
import com.juniorpaula.webserver.repositories.ClientRepository;
import com.juniorpaula.webserver.repositories.OrderItemRepository;
import com.juniorpaula.webserver.repositories.OrderRepository;
import com.juniorpaula.webserver.repositories.ProductRepository;
import com.juniorpaula.webserver.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
  
  @Autowired
  private OrderRepository repository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Autowired
  private ClientRepository clientRepository;

  public List<Order> findAll() {
    return repository.findAll();
  }

  public Order findById(Long id) {
    Optional<Order> obj = repository.findById(id);
    return obj.get();
  }

  public Order insert(RequestOrderDTO objDto) {
    Optional<Client> objClient = clientRepository.findById(objDto.clientId());
    if (!objClient.isPresent()) {
      throw new ResourceNotFoundException(objDto.clientId());
    }
    Client client = objClient.get();

    Order order = new Order(null, Instant.now(), OrderStatus.WAITING_PAYMENT, client);

    Optional<Product> objProduct = productRepository.findById(objDto.productId());
    if (!objProduct.isPresent()) {
      throw new ResourceNotFoundException(objDto.productId());
    }
    Product product = objProduct.get();

    OrderItem orderItem = new OrderItem(order, product, objDto.quantity(), product.getPrice());

    order.getItems().add(orderItem);
    repository.save(order);

    orderItemRepository.save(orderItem);

    return order;
  }
}
