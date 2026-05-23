package es.gestor.GestorTareas.business.application.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import es.gestor.GestorTareas.business.application.ports.in.task.CreateTaskUseCase;
import es.gestor.GestorTareas.business.application.ports.in.task.DeleteTaskUseCase;
import es.gestor.GestorTareas.business.application.ports.in.task.FindAllTaskUseCase;
import es.gestor.GestorTareas.business.application.ports.in.task.FindTaskByIdUseCase;
import es.gestor.GestorTareas.business.application.ports.in.task.UpdateTaskUseCase;
import es.gestor.GestorTareas.business.application.ports.out.TaskRepositoryPort;
import es.gestor.GestorTareas.business.domain.Task;
import es.gestor.GestorTareas.business.exception.InvalidTaskIdException;
import es.gestor.GestorTareas.business.exception.TaskNotFoundException;

@Service
public class TaskService
        implements CreateTaskUseCase, DeleteTaskUseCase, FindTaskByIdUseCase, UpdateTaskUseCase, FindAllTaskUseCase {

    private final TaskRepositoryPort taskRepository;

    public TaskService(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        if (task == null) {
            throw new TaskNotFoundException();
        }
        task.validateDueDate();
        return taskRepository.save(task);
    }

    @Override
    public Task findById(Long id) {
        if (id == null) {
            throw new InvalidTaskIdException();
        }
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException());
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task update(Long id, Task task) {

        if (id == null || task == null) {
            throw new InvalidTaskIdException();
        }

        Task taskFound = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException());

        taskFound.update(task);

        return taskRepository.save(taskFound);
    }

    @Override
    public void delete(Long id) {

        if (id == null) {
            throw new InvalidTaskIdException();
        }

        taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException());

        taskRepository.delete(id);
    }

}