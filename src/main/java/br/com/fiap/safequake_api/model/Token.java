package br.com.fiap.safequake_api.model;

public record Token(
    String token, 
    String type,
    String email
) {}