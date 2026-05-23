package es.gestor.GestorTareas.infrastructure.adapters.in.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import es.gestor.GestorTareas.business.domain.Task;
import es.gestor.GestorTareas.infrastructure.adapters.out.persistence.entity.TaskEntity;
import es.gestor.GestorTareas.infrastructure.dto.TaskRequest;

@Component
public class TaskInMapper {

    public Task toDomain(TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getTitle(),
                entity.getStatus(),
                entity.getPriority(),
                entity.getDueDate(),
                entity.getOwner().getUsername());
    }

    public List<Task> toDomainList(List<TaskEntity> entities) {

        return entities.stream()
                .map(entity -> new Task(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getStatus(),
                        entity.getPriority(),
                        entity.getDueDate(),
                        entity.getOwner().getUsername()))
                .toList();
    }

    public Task toDomainDto(TaskRequest request) {
        Task dto = new Task(null, request.getTitle(), request.getStatus(), request.getPriority(), request.getDueDate(),
                null);

        return dto;
    }
}
