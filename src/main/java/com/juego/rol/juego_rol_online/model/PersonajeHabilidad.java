package com.juego.rol.juego_rol_online.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PersonajeHabilidad {

    @EmbeddedId
    private PersonajeHabilidadId id;

    private int nivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personajeId")
    @JoinColumn(name = "personaje_id")
    private Personaje personaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("habilidadId")
    @JoinColumn(name = "habilidad_id")
    private Habilidad habilidad;

    // ========== CONSTRUCTOR VACÍO (REQUERIDO POR JPA) ==========
    public PersonajeHabilidad() {
    }

    // ========== CONSTRUCTOR CON PARÁMETROS ==========
    public PersonajeHabilidad(PersonajeHabilidadId id, int nivel, Personaje personaje, Habilidad habilidad) {
        this.id = id;
        this.nivel = nivel;
        this.personaje = personaje;
        this.habilidad = habilidad;
    }

    // ========== GETTERS Y SETTERS ==========
    public PersonajeHabilidadId getId() {
        return id;
    }

    public void setId(PersonajeHabilidadId id) {
        this.id = id;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }
}