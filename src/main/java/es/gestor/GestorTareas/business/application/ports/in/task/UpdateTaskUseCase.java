package es.gestor.GestorTareas.business.application.ports.in.task;

import es.gestor.GestorTareas.business.domain.Task;

public interface UpdateTaskUseCase {
    Task update(Long id, Task task);
}
