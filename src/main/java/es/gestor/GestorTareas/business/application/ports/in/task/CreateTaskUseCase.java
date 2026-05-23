package es.gestor.GestorTareas.business.application.ports.in.task;

import es.gestor.GestorTareas.business.domain.Task;

public interface CreateTaskUseCase {
    Task create(Task task);
}
