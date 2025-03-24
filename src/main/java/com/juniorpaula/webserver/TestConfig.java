package com.juniorpaula.webserver;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.juniorpaula.webserver.entities.Category;
import com.juniorpaula.webserver.entities.Order;
import com.juniorpaula.webserver.entities.Product;
import com.juniorpaula.webserver.entities.User;
import com.juniorpaula.webserver.entities.enums.OrderStatus;
import com.juniorpaula.webserver.repositories.CategoryRepository;
import com.juniorpaula.webserver.repositories.OrderRepository;
import com.juniorpaula.webserver.repositories.ProductRepository;
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

  @Autowired
  private ProductRepository productRepository;

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

    Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
    Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
    Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
    Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
    Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

    userRepository.saveAll(Arrays.asList(u1, u2));
    orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
    productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
  }
}
