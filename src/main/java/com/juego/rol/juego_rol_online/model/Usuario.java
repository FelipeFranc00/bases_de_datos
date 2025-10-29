package com.juego.rol.juego_rol_online.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Perfil perfil;

    // ========== CONSTRUCTOR VACÍO (REQUERIDO POR JPA) ==========
    public Usuario() {
    }

    // ========== CONSTRUCTOR CON PARÁMETROS ==========
    public Usuario(Long id, String username, String password, String email, Perfil perfil) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.perfil = perfil;
    }

    // ========== GETTERS Y SETTERS ==========
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}