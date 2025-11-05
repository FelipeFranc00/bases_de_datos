package com.juego.rol.juego_rol_online.dto;

import java.util.List;

/**
 * DTO para representar un personaje con todos sus detalles
 * incluyendo sus habilidades
 */
public class PersonajeDetalleDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private int ataqueBase;
    private int defensaBase;
    private int estaminaBase;
    private int ataqueTotal;
    private int defensaTotal;
    private int estaminaTotal;
    private Long perfilId;
    private List<PersonajeHabilidadDTO> habilidades;

    // Constructor vacío
    public PersonajeDetalleDTO() {
    }

    // Constructor completo
    public PersonajeDetalleDTO(Long id, String nombre, String descripcion, int ataqueBase,
            int defensaBase, int estaminaBase, int ataqueTotal,
            int defensaTotal, int estaminaTotal, Long perfilId,
            List<PersonajeHabilidadDTO> habilidades) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.estaminaBase = estaminaBase;
        this.ataqueTotal = ataqueTotal;
        this.defensaTotal = defensaTotal;
        this.estaminaTotal = estaminaTotal;
        this.perfilId = perfilId;
        this.habilidades = habilidades;
    }

    // Getters y Setters
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

    public int getAtaqueTotal() {
        return ataqueTotal;
    }

    public void setAtaqueTotal(int ataqueTotal) {
        this.ataqueTotal = ataqueTotal;
    }

    public int getDefensaTotal() {
        return defensaTotal;
    }

    public void setDefensaTotal(int defensaTotal) {
        this.defensaTotal = defensaTotal;
    }

    public int getEstaminaTotal() {
        return estaminaTotal;
    }

    public void setEstaminaTotal(int estaminaTotal) {
        this.estaminaTotal = estaminaTotal;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    public List<PersonajeHabilidadDTO> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<PersonajeHabilidadDTO> habilidades) {
        this.habilidades = habilidades;
    }
}