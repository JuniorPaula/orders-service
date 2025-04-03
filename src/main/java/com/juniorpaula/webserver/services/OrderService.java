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
    Client client = getClient(objDto.clientId());
    Product product = getProduct(objDto.productId());

    Order order = new Order(null, Instant.now(), OrderStatus.WAITING_PAYMENT, client);
    OrderItem orderItem = new OrderItem(order, product, objDto.quantity(), product.getPrice());

    order.getItems().add(orderItem);
    repository.save(order);
    orderItemRepository.save(orderItem);

    return order;
  }

  protected Client getClient(Long id) {
    Optional<Client> objClient = clientRepository.findById(id);
    if (!objClient.isPresent()) {
      throw new ResourceNotFoundException(id);
    }
    return objClient.get();
  }

  protected Product getProduct(Long id) {
    Optional<Product> objProduct = productRepository.findById(id);
    if (!objProduct.isPresent()) {
      throw new ResourceNotFoundException(id);
    }
    return objProduct.get();
  }
}
