package com.juego.rol.juego_rol_online.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "perfiles")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String biografia;
    private String avatarUrl; // URL de la imagen de perfil

    // Relación 1:1 con Usuario (dueño de la clave foránea)
    @OneToOne
    @JoinColumn(name = "usuario_id") // Columna de clave foránea en la tabla 'perfiles'
    private Usuario usuario;

    // Relación 1:M con Personaje
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Personaje> personajes;

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

    // Getters y Setters
    
}