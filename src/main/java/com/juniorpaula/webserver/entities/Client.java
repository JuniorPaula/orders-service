package com.juniorpaula.webserver.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  private String name;
  private String email;
  private String phone;

  @JsonIgnore
  @OneToMany(mappedBy = "client")
  private final List<Order> orders = new ArrayList<>();

  public Client() {
  }

  public Client(Long id, String name, String email, String phone) {
    Id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Order> getOrders() {
    return orders;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((Id == null) ? 0 : Id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Client client = (Client) o;

    return Id != null ? Id.equals(client.Id) : client.Id == null;
  }
}