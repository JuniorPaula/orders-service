package com.juniorpaula.webserver.dto;

public record LoginResponseDTO(String token, String name, String email, String role) {
}
