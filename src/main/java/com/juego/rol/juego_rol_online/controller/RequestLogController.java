package com.juego.rol.juego_rol_online.controller;

import com.juego.rol.juego_rol_online.model.RequestLog;
import com.juego.rol.juego_rol_online.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class RequestLogController {

    @Autowired
    private RequestLogRepository requestLogRepository;

    // ========== GET /logs - Listar todos los logs de peticiones ==========
    @GetMapping
    public ResponseEntity<List<RequestLog>> listarTodos() {
        List<RequestLog> logs = requestLogRepository.findAll();
        return ResponseEntity.ok(logs);
    }

    // ========== DELETE /logs - Limpiar todos los logs ==========
    @DeleteMapping
    public ResponseEntity<Void> limpiarLogs() {
        requestLogRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}