package com.juego.rol.juego_rol_online.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonajeHabilidadId implements Serializable {

    private Long personajeId;
    private Long habilidadId;

    // Es crucial implementar equals y hashCode para las claves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonajeHabilidadId that = (PersonajeHabilidadId) o;
        return Objects.equals(personajeId, that.personajeId) &&
               Objects.equals(habilidadId, that.habilidadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personajeId, habilidadId);
    }

    public PersonajeHabilidadId(Long personajeId, Long habilidadId) {
        this.personajeId = personajeId;
        this.habilidadId = habilidadId;
    }

    public Long getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(Long personajeId) {
        this.personajeId = personajeId;
    }

    public Long getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Long habilidadId) {
        this.habilidadId = habilidadId;
    }

    // Getters, Setters y Constructores
    
}
