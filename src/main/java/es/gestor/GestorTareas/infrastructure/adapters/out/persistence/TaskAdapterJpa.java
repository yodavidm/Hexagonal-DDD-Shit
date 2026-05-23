package es.gestor.GestorTareas.infrastructure.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import es.gestor.GestorTareas.business.application.ports.out.TaskRepositoryPort;
import es.gestor.GestorTareas.business.domain.Task;
import es.gestor.GestorTareas.infrastructure.adapters.in.mapper.TaskInMapper;
import es.gestor.GestorTareas.infrastructure.adapters.out.mapper.TaskOutMapper;
import es.gestor.GestorTareas.infrastructure.adapters.out.persistence.entity.TaskEntity;
import es.gestor.GestorTareas.infrastructure.adapters.out.persistence.entity.UserEntity;
import es.gestor.GestorTareas.infrastructure.adapters.out.persistence.repository.TaskRepositoryJpa;
import es.gestor.GestorTareas.infrastructure.adapters.out.persistence.repository.UserRepositoryJpa;

@Component
public class TaskAdapterJpa implements TaskRepositoryPort {

    private UserRepositoryJpa userRepository;
    private TaskRepositoryJpa taskRepository;
    private TaskOutMapper taskOutMapper;
    private TaskInMapper taskInMapper;

    public TaskAdapterJpa(TaskRepositoryJpa taskRepository, TaskOutMapper taskOutMapper, TaskInMapper taskInMapper,
            UserRepositoryJpa userRepository) {
        this.taskRepository = taskRepository;
        this.taskOutMapper = taskOutMapper;
        this.taskInMapper = taskInMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Task save(Task task) {
        TaskEntity toSave = taskOutMapper.fromDomain(task);

        UserEntity user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("No se encontró usuario"));

        toSave.setOwner(user);

        TaskEntity created = taskRepository.save(toSave);

        return taskInMapper.toDomain(created);

    }

    @Override
    public void delete(Long id) {
        TaskEntity taskFound = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró tarea"));
        taskRepository.delete(taskFound);

    }

    @Override
    public Optional<Task> findById(Long id) {

        return taskRepository.findById(id)
                .map(taskInMapper::toDomain);
    }

    @Override
    public List<Task> findAll() {
        List<TaskEntity> tasks = taskRepository.findAll();
        List<Task> list = taskInMapper.toDomainList(tasks);

        return list;
    }
}
