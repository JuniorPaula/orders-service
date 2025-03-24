package com.juniorpaula.webserver;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.juniorpaula.webserver.entities.Category;
import com.juniorpaula.webserver.entities.Order;
import com.juniorpaula.webserver.entities.User;
import com.juniorpaula.webserver.entities.enums.OrderStatus;
import com.juniorpaula.webserver.repositories.CategoryRepository;
import com.juniorpaula.webserver.repositories.OrderRepository;
import com.juniorpaula.webserver.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public void run(String... args) throws Exception {
    User u1 = new User(null, "Maria Brown", "maria@email.com", "988888888", "123456");
    User u2 = new User(null, "Alex Green", "alex@email.com", "977777777", "123456");
  
    Order o1 = new Order(null, Instant.parse("2025-02-02T15:42:07Z"), OrderStatus.PAID, u1);
    Order o2 = new Order(null, Instant.parse("2025-02-03T15:42:07Z"), OrderStatus.WAITING_PAYMENT, u2);
    Order o3 = new Order(null, Instant.parse("2025-02-04T15:42:07Z"), OrderStatus.WAITING_PAYMENT, u1);

    Category cat1 = new Category(null, "Electronics");
    Category cat2 = new Category(null, "Books");
    Category cat3 = new Category(null, "Computers");

    userRepository.saveAll(Arrays.asList(u1, u2));
    orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
  }
}
