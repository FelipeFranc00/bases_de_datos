package com.juego.rol.juego_rol_online.filter;

import com.juego.rol.juego_rol_online.model.RequestLog;
import com.juego.rol.juego_rol_online.repository.RequestLogRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class RequestLoggingFilter implements Filter {

    @Autowired
    private RequestLogRepository logRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Crear el log de la petición
        RequestLog log = new RequestLog();
        log.setMethod(httpRequest.getMethod());
        log.setPath(httpRequest.getRequestURI());
        log.setIp(httpRequest.getRemoteAddr());
        log.setTimestamp(LocalDateTime.now());

        // Guardar en la base de datos
        logRepository.save(log);

        // Continuar con la cadena de filtros
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización si es necesaria
    }

    @Override
    public void destroy() {
        // Limpieza si es necesaria
    }
}