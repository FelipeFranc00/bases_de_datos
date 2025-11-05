package com.juego.rol.juego_rol_online.controller;

import com.juego.rol.juego_rol_online.model.Perfil;
import com.juego.rol.juego_rol_online.model.Usuario;
import com.juego.rol.juego_rol_online.repository.PerfilRepository;
import com.juego.rol.juego_rol_online.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ========== GET /perfiles - Listar todos los perfiles ==========
    @GetMapping
    public ResponseEntity<List<Perfil>> listarTodos() {
        List<Perfil> perfiles = perfilRepository.findAll();
        return ResponseEntity.ok(perfiles);
    }

    // ========== GET /perfiles/{id} - Obtener un perfil por ID ==========
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> obtenerPorId(@PathVariable Long id) {
        Optional<Perfil> perfil = perfilRepository.findById(id);

        if (perfil.isPresent()) {
            return ResponseEntity.ok(perfil.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== POST /perfiles - Crear un nuevo perfil ==========
    @PostMapping
    public ResponseEntity<Perfil> crear(@RequestBody PerfilRequest request) {
        // Validar que existe el usuario
        Optional<Usuario> usuario = usuarioRepository.findById(request.getUsuarioId());

        if (!usuario.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        // Crear el perfil
        Perfil nuevoPerfil = new Perfil(
                null,
                request.getBiografia(),
                request.getAvatarUrl(),
                usuario.get(),
                null);

        Perfil guardado = perfilRepository.save(nuevoPerfil);

        // Actualizar usuario con referencia al perfil
        usuario.get().setPerfil(guardado);
        usuarioRepository.save(usuario.get());

        return ResponseEntity.ok(guardado);
    }

    // ========== PUT /perfiles/{id} - Editar un perfil ==========
    @PutMapping("/{id}")
    public ResponseEntity<Perfil> editar(@PathVariable Long id, @RequestBody PerfilRequest request) {
        Optional<Perfil> perfilOpt = perfilRepository.findById(id);

        if (!perfilOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Perfil perfil = perfilOpt.get();

        // Actualizar campos
        if (request.getBiografia() != null)
            perfil.setBiografia(request.getBiografia());
        if (request.getAvatarUrl() != null)
            perfil.setAvatarUrl(request.getAvatarUrl());

        Perfil actualizado = perfilRepository.save(perfil);
        return ResponseEntity.ok(actualizado);
    }

    // ========== DELETE /perfiles/{id} - Eliminar un perfil ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!perfilRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        perfilRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ==================== CLASE INTERNA PARA REQUEST ====================
    public static class PerfilRequest {
        private String biografia;
        private String avatarUrl;
        private Long usuarioId;

        // Getters y Setters
        public String getBiografia() {
            return biografia;
        }

        public void setBiografia(String biografia) {
            this.biografia = biografia;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public Long getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Long usuarioId) {
            this.usuarioId = usuarioId;
        }
    }
}