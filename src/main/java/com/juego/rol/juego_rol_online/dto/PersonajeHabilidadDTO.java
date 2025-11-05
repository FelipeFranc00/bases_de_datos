package com.juego.rol.juego_rol_online.dto;

/**
 * DTO para representar la relación entre Personaje y Habilidad
 * con información completa para la respuesta JSON
 */
public class PersonajeHabilidadDTO {
    private Long habilidadId;
    private String nombreHabilidad;
    private String descripcionHabilidad;
    private int nivel;
    private int incrementoAtaque;
    private int incrementoDefensa;
    private int incrementoEstamina;

    // Constructor vacío
    public PersonajeHabilidadDTO() {
    }

    // Constructor completo
    public PersonajeHabilidadDTO(Long habilidadId, String nombreHabilidad, String descripcionHabilidad,
            int nivel, int incrementoAtaque, int incrementoDefensa, int incrementoEstamina) {
        this.habilidadId = habilidadId;
        this.nombreHabilidad = nombreHabilidad;
        this.descripcionHabilidad = descripcionHabilidad;
        this.nivel = nivel;
        this.incrementoAtaque = incrementoAtaque;
        this.incrementoDefensa = incrementoDefensa;
        this.incrementoEstamina = incrementoEstamina;
    }

    // Getters y Setters
    public Long getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Long habilidadId) {
        this.habilidadId = habilidadId;
    }

    public String getNombreHabilidad() {
        return nombreHabilidad;
    }

    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }

    public String getDescripcionHabilidad() {
        return descripcionHabilidad;
    }

    public void setDescripcionHabilidad(String descripcionHabilidad) {
        this.descripcionHabilidad = descripcionHabilidad;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
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
}