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

        RequestLog log = new RequestLog();
        log.setMethod(httpRequest.getMethod());
        log.setPath(httpRequest.getRequestURI());
        log.setIp(httpRequest.getRemoteAddr());
        log.setTimestamp(LocalDateTime.now());

        logRepository.save(log);

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}