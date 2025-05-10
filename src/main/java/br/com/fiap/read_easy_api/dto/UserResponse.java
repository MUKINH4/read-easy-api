package br.com.fiap.read_easy_api.dto;

import br.com.fiap.read_easy_api.model.enums.UserRole;

public record UserResponse(String id, String email, UserRole role) {
    
}
