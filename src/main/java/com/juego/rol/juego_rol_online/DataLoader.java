package com.juego.rol.juego_rol_online;

import com.juego.rol.juego_rol_online.model.*;
import com.juego.rol.juego_rol_online.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initDatabase(
            UsuarioRepository usuarioRepository,
            PerfilRepository perfilRepository,
            PersonajeRepository personajeRepository,
            HabilidadRepository habilidadRepository,
            PersonajeHabilidadRepository personajeHabilidadRepository
    ) {
        return args -> {
            // --- 1. CARGAR HABILIDADES INICIALES ---
            Habilidad fuego = new Habilidad(null, "Bola de Fuego", "Daño elemental", 15, 0, -5, null);
            Habilidad defensa = new Habilidad(null, "Escudo Divino", "Aumenta la resistencia", 0, 20, -10, null);
            habilidadRepository.saveAll(Arrays.asList(fuego, defensa));

            // --- 2. CREAR USUARIO Y PERFIL ---
            Usuario user1 = new Usuario(null, "jugador1", "12345", "a@a.com", null);
            usuarioRepository.save(user1);

            Perfil perfil1 = new Perfil(null, "Aventurero novato", "url/avatar1.jpg", user1, null);
            perfilRepository.save(perfil1);
            user1.setPerfil(perfil1);
            usuarioRepository.save(user1); // Actualizar usuario con referencia al perfil

            // --- 3. CREAR PERSONAJE ---
            Personaje pj1 = new Personaje(null, "Gryphon", "Caballero legendario", 50, 60, 40, perfil1, null);
            personajeRepository.save(pj1);

            // --- 4. ASIGNAR HABILIDADES AL PERSONAJE (M:M con NIVEL) ---
            
            // Asignar Bola de Fuego (ID 1) a Gryphon (Nivel 3)
            PersonajeHabilidadId idFuego = new PersonajeHabilidadId(pj1.getId(), fuego.getId());
            PersonajeHabilidad phFuego = new PersonajeHabilidad(idFuego, 3, pj1, fuego);
            personajeHabilidadRepository.save(phFuego);

            // Asignar Escudo Divino (ID 2) a Gryphon (Nivel 1)
            PersonajeHabilidadId idDefensa = new PersonajeHabilidadId(pj1.getId(), defensa.getId());
            PersonajeHabilidad phDefensa = new PersonajeHabilidad(idDefensa, 1, pj1, defensa);
            personajeHabilidadRepository.save(phDefensa);

            System.out.println(">>> Base de datos inicializada con éxito.");
        };
    }
}