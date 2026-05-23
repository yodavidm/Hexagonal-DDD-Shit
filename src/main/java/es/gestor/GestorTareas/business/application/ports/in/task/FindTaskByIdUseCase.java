package es.gestor.GestorTareas.business.application.ports.in.task;

import java.util.Optional;

import es.gestor.GestorTareas.business.domain.Task;

public interface FindTaskByIdUseCase {
    Task findById(Long id);
}
