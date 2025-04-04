package com.juniorpaula.webserver.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniorpaula.webserver.dto.RequestOrderDTO;
import com.juniorpaula.webserver.entities.Order;
import com.juniorpaula.webserver.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResouce {

  @Autowired
  private OrderService service;

  @GetMapping
  public ResponseEntity<List<Order>> findAll() {

    List<Order> orders = service.findAll();

    return ResponseEntity.ok().body(orders);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Order> findById(@PathVariable Long id) {

    Order obj = service.findById(id);

    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  public ResponseEntity<Order> insert(@RequestBody RequestOrderDTO request) {

    Order obj = service.insert(request);

    return ResponseEntity.ok().body(obj);
  }

  @PostMapping(value = "/{id}/payment")
  public ResponseEntity<Order> processPayment(@PathVariable Long id) {

    Order obj = service.processPayment(id);

    return ResponseEntity.ok().body(obj);
  }
}
