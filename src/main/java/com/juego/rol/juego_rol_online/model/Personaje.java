package com.juego.rol.juego_rol_online.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private int ataqueBase;
    private int defensaBase;
    private int estaminaBase;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    @JsonIgnore
    private Perfil perfil;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PersonajeHabilidad> personajeHabilidades;

    public Personaje() {
    }

    // ========== CONSTRUCTOR CON PARÁMETROS ==========
    public Personaje(Long id, String nombre, String descripcion, int ataqueBase, int defensaBase, int estaminaBase,
            Perfil perfil, List<PersonajeHabilidad> personajeHabilidades) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.estaminaBase = estaminaBase;
        this.perfil = perfil;
        this.personajeHabilidades = personajeHabilidades;
    }

    // ========== GETTERS Y SETTERS ==========
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAtaqueBase() {
        return ataqueBase;
    }

    public void setAtaqueBase(int ataqueBase) {
        this.ataqueBase = ataqueBase;
    }

    public int getDefensaBase() {
        return defensaBase;
    }

    public void setDefensaBase(int defensaBase) {
        this.defensaBase = defensaBase;
    }

    public int getEstaminaBase() {
        return estaminaBase;
    }

    public void setEstaminaBase(int estaminaBase) {
        this.estaminaBase = estaminaBase;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<PersonajeHabilidad> getPersonajeHabilidades() {
        return personajeHabilidades;
    }

    public void setPersonajeHabilidades(List<PersonajeHabilidad> personajeHabilidades) {
        this.personajeHabilidades = personajeHabilidades;
    }
}