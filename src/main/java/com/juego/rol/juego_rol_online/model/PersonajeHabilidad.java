package com.juego.rol.juego_rol_online.model;

import jakarta.persistence.*;

@Entity
@Table(name = "personaje_habilidad")
public class PersonajeHabilidad {

    // Clave compuesta definida en la clase anterior
    @EmbeddedId
    private PersonajeHabilidadId id;

    // Atributo adicional requerido: el nivel
    private int nivel; 

    // Relación ManyToOne con Personaje
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personajeId") // Mapea el campo al ID embebido
    @JoinColumn(name = "personaje_id")
    private Personaje personaje;

    // Relación ManyToOne con Habilidad
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("habilidadId") // Mapea el campo al ID embebido
    @JoinColumn(name = "habilidad_id")
    private Habilidad habilidad;

    public PersonajeHabilidad(PersonajeHabilidadId id, int nivel, Personaje personaje, Habilidad habilidad) {
        this.id = id;
        this.nivel = nivel;
        this.personaje = personaje;
        this.habilidad = habilidad;
    }

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

    // Getters, Setters y Constructores
    
}