package com.juniorpaula.webserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RequestOrderDTO(
  @NotBlank Long productId,
  @NotBlank @Positive Integer quantity
) {
 
}