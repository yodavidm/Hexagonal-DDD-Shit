package es.gestor.GestorTareas.infrastructure.adapters.out.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import es.gestor.GestorTareas.business.domain.Task;
import es.gestor.GestorTareas.infrastructure.adapters.out.persistence.entity.TaskEntity;
import es.gestor.GestorTareas.infrastructure.adapters.out.persistence.entity.UserEntity;
import es.gestor.GestorTareas.infrastructure.dto.TaskResponse;

@Component
public class TaskOutMapper {

    public TaskEntity fromDomain(Task domain) {
        TaskEntity entity = new TaskEntity(domain.getId(), domain.getTitle(), domain.getStatus(), domain.getPriority(),
                domain.getDueDate(), new UserEntity());

        return entity;
    }

    public TaskResponse fromDomainResponse(Task domain) {
        TaskResponse response = new TaskResponse(domain.getId(), domain.getTitle(), domain.getStatus(),
                domain.getPriority(), domain.getDueDate(), domain.getCreatedBy());
        return response;
    }

    public List<TaskResponse> fromDomainResponse(List<Task> domain) {
        List<TaskResponse> response = domain.stream()
                .map(task -> new TaskResponse(task
                        .getId(), task.getTitle(), task.getStatus(), task.getPriority(), task.getDueDate(),
                        task.getCreatedBy()))
                .toList();
        return response;
    }
}
