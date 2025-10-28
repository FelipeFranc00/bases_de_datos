package com.juego.rol.juego_rol_online.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "habilidades")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private int incrementoAtaque;
    private int incrementoDefensa;
    private int incrementoEstamina;

    // Relación 1:M con la tabla de unión PersonajeHabilidad
    @OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonajeHabilidad> personajeHabilidades;

    public Habilidad(Long id, String nombre, String descripcion, int incrementoAtaque, int incrementoDefensa,
            int incrementoEstamina, List<PersonajeHabilidad> personajeHabilidades) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.incrementoAtaque = incrementoAtaque;
        this.incrementoDefensa = incrementoDefensa;
        this.incrementoEstamina = incrementoEstamina;
        this.personajeHabilidades = personajeHabilidades;
    }

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

    public int getIncrementoAtaque() {
        return incrementoAtaque;
    }

    public void setIncrementoAtaque(int incrementoAtaque) {
        this.incrementoAtaque = incrementoAtaque;
    }

    public int getIncrementoDefensa() {
        return incrementoDefensa;
    }

    public void setIncrementoDefensa(int incrementoDefensa) {
        this.incrementoDefensa = incrementoDefensa;
    }

    public int getIncrementoEstamina() {
        return incrementoEstamina;
    }

    public void setIncrementoEstamina(int incrementoEstamina) {
        this.incrementoEstamina = incrementoEstamina;
    }

    public List<PersonajeHabilidad> getPersonajeHabilidades() {
        return personajeHabilidades;
    }

    public void setPersonajeHabilidades(List<PersonajeHabilidad> personajeHabilidades) {
        this.personajeHabilidades = personajeHabilidades;
    }

    

    // Getters y Setters
    
}