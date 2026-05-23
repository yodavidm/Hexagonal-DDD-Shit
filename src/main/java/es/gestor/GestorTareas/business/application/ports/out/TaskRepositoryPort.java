package es.gestor.GestorTareas.business.application.ports.out;

import java.util.List;
import java.util.Optional;

import es.gestor.GestorTareas.business.domain.Task;

public interface TaskRepositoryPort {
    Task save(Task task);

    void delete(Long id);

    Optional<Task> findById(Long id);

    List<Task> findAll();
}
