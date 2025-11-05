package com.juego.rol.juego_rol_online.controller;

import com.juego.rol.juego_rol_online.model.*;
import com.juego.rol.juego_rol_online.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private HabilidadRepository habilidadRepository;

    @Autowired
    private PersonajeHabilidadRepository personajeHabilidadRepository;

    // ========== GET /personajes - Listar todos los personajes ==========
    @GetMapping
    public ResponseEntity<List<Personaje>> listarTodos() {
        List<Personaje> personajes = personajeRepository.findAll();
        return ResponseEntity.ok(personajes);
    }

    // ========== GET /personajes/{id} - Obtener detalles de un personaje ==========
    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPorId(@PathVariable Long id) {
        Optional<Personaje> personaje = personajeRepository.findById(id);

        if (personaje.isPresent()) {
            return ResponseEntity.ok(personaje.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== GET /personajes/{id}/habilidades - Obtener habilidades del
    // personaje ==========
    @GetMapping("/{id}/habilidades")
    public ResponseEntity<List<PersonajeHabilidad>> obtenerHabilidadesDePersonaje(@PathVariable Long id) {
        Optional<Personaje> personajeOpt = personajeRepository.findById(id);

        if (!personajeOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Personaje personaje = personajeOpt.get();
        List<PersonajeHabilidad> habilidades = personaje.getPersonajeHabilidades();

        return ResponseEntity.ok(habilidades);
    }

    // ========== POST /personajes - Crear un nuevo personaje ==========
    @PostMapping
    public ResponseEntity<Personaje> crear(@RequestBody PersonajeRequest request) {
        // Validar que existe el perfil
        Optional<Perfil> perfil = perfilRepository.findById(request.getPerfilId());

        if (!perfil.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        // Crear el personaje
        Personaje nuevoPersonaje = new Personaje(
                null,
                request.getNombre(),
                request.getDescripcion(),
                request.getAtaqueBase(),
                request.getDefensaBase(),
                request.getEstaminaBase(),
                perfil.get(),
                null);

        Personaje guardado = personajeRepository.save(nuevoPersonaje);
        return ResponseEntity.ok(guardado);
    }

    // ========== PUT /personajes/{id} - Editar un personaje ==========
    @PutMapping("/{id}")
    public ResponseEntity<Personaje> editar(@PathVariable Long id, @RequestBody PersonajeRequest request) {
        Optional<Personaje> personajeOpt = personajeRepository.findById(id);

        if (!personajeOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Personaje personaje = personajeOpt.get();

        // Actualizar campos
        if (request.getNombre() != null)
            personaje.setNombre(request.getNombre());
        if (request.getDescripcion() != null)
            personaje.setDescripcion(request.getDescripcion());
        if (request.getAtaqueBase() != 0)
            personaje.setAtaqueBase(request.getAtaqueBase());
        if (request.getDefensaBase() != 0)
            personaje.setDefensaBase(request.getDefensaBase());
        if (request.getEstaminaBase() != 0)
            personaje.setEstaminaBase(request.getEstaminaBase());

        Personaje actualizado = personajeRepository.save(personaje);
        return ResponseEntity.ok(actualizado);
    }

    // ========== DELETE /personajes/{id} - Eliminar un personaje ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!personajeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        personajeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ========== POST /personajes/{id}/habilidades - Agregar habilidad a personaje
    // ==========
    @PostMapping("/{id}/habilidades")
    public ResponseEntity<PersonajeHabilidad> agregarHabilidad(
            @PathVariable Long id,
            @RequestBody AgregarHabilidadRequest request) {

        // Validar que existen el personaje y la habilidad
        Optional<Personaje> personajeOpt = personajeRepository.findById(id);
        Optional<Habilidad> habilidadOpt = habilidadRepository.findById(request.getHabilidadId());

        if (!personajeOpt.isPresent() || !habilidadOpt.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Personaje personaje = personajeOpt.get();
        Habilidad habilidad = habilidadOpt.get();

        // Crear la relación
        PersonajeHabilidadId phId = new PersonajeHabilidadId(id, request.getHabilidadId());
        PersonajeHabilidad ph = new PersonajeHabilidad(
                phId,
                request.getNivel(),
                personaje,
                habilidad);

        PersonajeHabilidad guardado = personajeHabilidadRepository.save(ph);
        return ResponseEntity.ok(guardado);
    }

    // ========== DELETE /personajes/{idPersonaje}/habilidades/{idHabilidad} -
    // Quitar habilidad ==========
    @DeleteMapping("/{idPersonaje}/habilidades/{idHabilidad}")
    public ResponseEntity<Void> quitarHabilidad(
            @PathVariable Long idPersonaje,
            @PathVariable Long idHabilidad) {

        PersonajeHabilidadId phId = new PersonajeHabilidadId(idPersonaje, idHabilidad);

        if (!personajeHabilidadRepository.existsById(phId)) {
            return ResponseEntity.notFound().build();
        }

        personajeHabilidadRepository.deleteById(phId);
        return ResponseEntity.noContent().build();
    }

    // ==================== CLASES INTERNAS PARA REQUEST ====================

    // Clase para recibir datos de creación/edición de personaje
    public static class PersonajeRequest {
        private String nombre;
        private String descripcion;
        private int ataqueBase;
        private int defensaBase;
        private int estaminaBase;
        private Long perfilId;

        // Getters y Setters
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

        public Long getPerfilId() {
            return perfilId;
        }

        public void setPerfilId(Long perfilId) {
            this.perfilId = perfilId;
        }
    }

    // Clase para recibir datos de agregar habilidad
    public static class AgregarHabilidadRequest {
        private Long habilidadId;
        private int nivel;

        // Getters y Setters
        public Long getHabilidadId() {
            return habilidadId;
        }

        public void setHabilidadId(Long habilidadId) {
            this.habilidadId = habilidadId;
        }

        public int getNivel() {
            return nivel;
        }

        public void setNivel(int nivel) {
            this.nivel = nivel;
        }
    }
}