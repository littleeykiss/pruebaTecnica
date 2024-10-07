package com.pruebaTecnica.init.entities;

public class AuthenticationRequest {

    private String username;
    private String password;

    // Constructor por defecto
    public AuthenticationRequest() {}

    // Constructor con parámetros
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}