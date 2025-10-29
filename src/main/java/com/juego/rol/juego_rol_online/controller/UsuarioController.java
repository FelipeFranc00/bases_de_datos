package com.juego.rol.juego_rol_online.controller;

import com.juego.rol.juego_rol_online.model.Personaje;
import com.juego.rol.juego_rol_online.model.Usuario;
import com.juego.rol.juego_rol_online.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ========== GET /usuarios - Listar todos los usuarios ==========
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // ========== GET /usuarios/{id} - Obtener un usuario por ID ==========
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== GET /usuarios/{id}/personajes - Obtener personajes de un usuario
    // ==========
    @GetMapping("/{id}/personajes")
    public ResponseEntity<List<Personaje>> obtenerPersonajesPorUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOpt.get();

        // Verificar que el usuario tiene perfil
        if (usuario.getPerfil() == null) {
            return ResponseEntity.ok(List.of()); // Lista vacía si no tiene perfil
        }

        // Obtener los personajes del perfil del usuario
        List<Personaje> personajes = usuario.getPerfil().getPersonajes();
        return ResponseEntity.ok(personajes);
    }

    // ========== POST /usuarios - Crear un nuevo usuario ==========
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody UsuarioRequest request) {
        Usuario nuevoUsuario = new Usuario(
                null,
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                null);

        Usuario guardado = usuarioRepository.save(nuevoUsuario);
        return ResponseEntity.ok(guardado);
    }

    // ==================== CLASE INTERNA PARA REQUEST ====================

    public static class UsuarioRequest {
        private String username;
        private String password;
        private String email;

        // Getters y Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}