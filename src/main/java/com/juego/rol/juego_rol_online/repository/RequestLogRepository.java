package com.juego.rol.juego_rol_online.repository;

import com.juego.rol.juego_rol_online.model.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {

}
