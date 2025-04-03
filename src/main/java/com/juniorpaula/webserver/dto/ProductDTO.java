package com.juniorpaula.webserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductDTO(
  @NotBlank String name,
  @NotBlank String description,
  @NotNull @Positive Double price,
  String imageUrl,
  @NotNull @Size(min = 1, message = "Provide at least one category") Long[] categoriesIds
) {}