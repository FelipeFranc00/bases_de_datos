package com.juego.rol.juego_rol_online.Filter;

import com.juego.rol.juego_rol_online.model.RequestLog;
import com.juego.rol.juego_rol_online.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class RequestLogginFilter implements Filter {
    @Autowired
    private RequestLogRepository LogRepository;

}
