package com.juego.rol.juego_rol_online.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "personajes" })
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String biografia;
    private String avatarUrl;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Personaje> personajes;

    // ========== CONSTRUCTOR VACÍO (REQUERIDO POR JPA) ==========
    public Perfil() {
    }

    // ========== CONSTRUCTOR CON PARÁMETROS ==========
    public Perfil(Long id, String biografia, String avatarUrl, Usuario usuario, List<Personaje> personajes) {
        this.id = id;
        this.biografia = biografia;
        this.avatarUrl = avatarUrl;
        this.usuario = usuario;
        this.personajes = personajes;
    }

    // ========== GETTERS Y SETTERS ==========
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
}