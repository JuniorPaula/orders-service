package com.juniorpaula.webserver.dto;

import com.juniorpaula.webserver.entities.enums.UserRole;

public record  RegisterDTO(String name, String email, String phone, String password, UserRole role) {

}
