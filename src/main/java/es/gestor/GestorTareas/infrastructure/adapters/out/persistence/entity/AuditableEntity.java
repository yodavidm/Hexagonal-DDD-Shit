package es.gestor.GestorTareas.infrastructure.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditableEntity {

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
