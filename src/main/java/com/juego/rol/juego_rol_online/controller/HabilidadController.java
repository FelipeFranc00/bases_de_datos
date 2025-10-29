package com.juego.rol.juego_rol_online.controller;

import com.juego.rol.juego_rol_online.model.Habilidad;
import com.juego.rol.juego_rol_online.repository.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habilidades")
public class HabilidadController {

    @Autowired
    private HabilidadRepository habilidadRepository;

    // ========== GET /habilidades - Listar todas las habilidades ==========
    @GetMapping
    public ResponseEntity<List<Habilidad>> listarTodas() {
        List<Habilidad> habilidades = habilidadRepository.findAll();
        return ResponseEntity.ok(habilidades);
    }

    // ========== GET /habilidades/{id} - Obtener una habilidad por ID ==========
    @GetMapping("/{id}")
    public ResponseEntity<Habilidad> obtenerPorId(@PathVariable Long id) {
        Optional<Habilidad> habilidad = habilidadRepository.findById(id);

        if (habilidad.isPresent()) {
            return ResponseEntity.ok(habilidad.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== POST /habilidades - Crear una nueva habilidad ==========
    @PostMapping
    public ResponseEntity<Habilidad> crear(@RequestBody HabilidadRequest request) {
        Habilidad nuevaHabilidad = new Habilidad(
                null,
                request.getNombre(),
                request.getDescripcion(),
                request.getIncrementoAtaque(),
                request.getIncrementoDefensa(),
                request.getIncrementoEstamina(),
                null);

        Habilidad guardada = habilidadRepository.save(nuevaHabilidad);
        return ResponseEntity.ok(guardada);
    }

    // ========== PUT /habilidades/{id} - Editar una habilidad ==========
    @PutMapping("/{id}")
    public ResponseEntity<Habilidad> editar(@PathVariable Long id, @RequestBody HabilidadRequest request) {
        Optional<Habilidad> habilidadOpt = habilidadRepository.findById(id);

        if (!habilidadOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Habilidad habilidad = habilidadOpt.get();

        // Actualizar campos
        if (request.getNombre() != null)
            habilidad.setNombre(request.getNombre());
        if (request.getDescripcion() != null)
            habilidad.setDescripcion(request.getDescripcion());

        habilidad.setIncrementoAtaque(request.getIncrementoAtaque());
        habilidad.setIncrementoDefensa(request.getIncrementoDefensa());
        habilidad.setIncrementoEstamina(request.getIncrementoEstamina());

        Habilidad actualizada = habilidadRepository.save(habilidad);
        return ResponseEntity.ok(actualizada);
    }

    // ========== DELETE /habilidades/{id} - Eliminar una habilidad ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!habilidadRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        habilidadRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ==================== CLASE INTERNA PARA REQUEST ====================
    public static class HabilidadRequest {
        private String nombre;
        private String descripcion;
        private int incrementoAtaque;
        private int incrementoDefensa;
        private int incrementoEstamina;

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
}