package com.juego.rol.juego_rol_online.repository;

import com.juego.rol.juego_rol_online.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí se pueden añadir métodos de consulta personalizados,
    // ej: Usuario findByUsername(String username);
}