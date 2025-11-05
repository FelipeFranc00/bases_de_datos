package com.juego.rol.juego_rol_online.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(2) // Puedes ajustar el orden si tienes más filtros
public class ResponseSanitizationFilter implements Filter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 1. Envolver la respuesta para capturar el contenido
        ContentCachingResponseWrapper responseWrapper = 
            new ContentCachingResponseWrapper((HttpServletResponse) response);

        // 2. Permitir que la cadena de filtros se ejecute
        chain.doFilter(request, responseWrapper);

        // 3. Post-procesamiento: Sanitizar solo si es JSON
        byte[] responseBody = responseWrapper.getContentAsByteArray();
        if (responseBody.length > 0 && responseWrapper.getContentType() != null && 
            responseWrapper.getContentType().contains("application/json")) {
            
            // Procesar y sobrescribir el JSON
            byte[] sanitizedBody = sanitizeJsonResponse(responseBody);
            
            // Escribir la respuesta sanitizada en la respuesta original
            response.setContentLength(sanitizedBody.length);
            try (OutputStream os = response.getOutputStream()) {
                os.write(sanitizedBody);
            }
        } else {
            // Si no es JSON, copiar el contenido original sin modificar
            responseWrapper.copyBodyToResponse();
        }
    }

    private byte[] sanitizeJsonResponse(byte[] originalBody) throws IOException {
        try {
            JsonNode rootNode = objectMapper.readTree(originalBody);
            processNode(rootNode);
            return objectMapper.writeValueAsBytes(rootNode);
        } catch (Exception e) {
            // Manejar errores de parsing si el cuerpo no es JSON válido
            System.err.println("Error sanitizando JSON: " + e.getMessage());
            return originalBody; 
        }
    }

    /**
     * Función recursiva CORREGIDA para eliminar "id" y "*_id" de todos los objetos.
     */
    private void processNode(JsonNode node) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            
            // Almacenar los nombres de campos a eliminar
            List<String> fieldsToRemove = new ArrayList<>();
            
            objectNode.fieldNames().forEachRemaining(fieldName -> {
                String lowerCaseName = fieldName.toLowerCase();
                
                // --- LÓGICA DE SANITIZACIÓN CORREGIDA ---
                // 1. Elimina el campo primario "id"
                // 2. Elimina cualquier clave foránea que termine en "_id"
                if (lowerCaseName.equals("id") || lowerCaseName.endsWith("_id")) {
                    fieldsToRemove.add(fieldName);
                }
            });
            
            // Eliminar los campos después de la iteración
            objectNode.remove(fieldsToRemove);

            // Procesar campos anidados recursivamente
            objectNode.forEach(this::processNode);

        } else if (node.isArray()) {
            // Si es un array, procesa cada elemento
            node.forEach(this::processNode);
        }
    }
    
    // Los métodos init() y destroy() de la interfaz Filter se omiten por ser opcionales.
}