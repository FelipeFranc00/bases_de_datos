package com.juego.rol.juego_rol_online.repository;

import com.juego.rol.juego_rol_online.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    
}
