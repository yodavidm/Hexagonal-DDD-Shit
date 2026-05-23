package es.gestor.GestorTareas.infrastructure.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.gestor.GestorTareas.infrastructure.adapters.out.persistence.entity.TaskEntity;

@Repository
public interface TaskRepositoryJpa extends JpaRepository<TaskEntity,Long> {
    
}
