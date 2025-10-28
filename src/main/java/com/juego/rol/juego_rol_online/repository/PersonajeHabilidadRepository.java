package com.juego.rol.juego_rol_online.repository;

import com.juego.rol.juego_rol_online.model.PersonajeHabilidad;
import com.juego.rol.juego_rol_online.model.PersonajeHabilidadId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeHabilidadRepository extends JpaRepository<PersonajeHabilidad, PersonajeHabilidadId> {
    // Esto permitirá buscar, guardar o eliminar entradas en la tabla de unión.
}
