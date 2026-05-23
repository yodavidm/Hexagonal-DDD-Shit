package es.gestor.GestorTareas.business.application.ports.in.task;

import java.util.List;

import es.gestor.GestorTareas.business.domain.Task;

public interface FindAllTaskUseCase {
    List<Task> findAll();
}
