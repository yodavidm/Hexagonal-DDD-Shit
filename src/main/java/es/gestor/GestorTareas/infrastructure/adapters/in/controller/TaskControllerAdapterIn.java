package es.gestor.GestorTareas.infrastructure.adapters.in.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.gestor.GestorTareas.business.application.ports.in.task.CreateTaskUseCase;
import es.gestor.GestorTareas.business.application.ports.in.task.DeleteTaskUseCase;
import es.gestor.GestorTareas.business.application.ports.in.task.FindAllTaskUseCase;
import es.gestor.GestorTareas.business.application.ports.in.task.FindTaskByIdUseCase;
import es.gestor.GestorTareas.business.application.ports.in.task.UpdateTaskUseCase;
import es.gestor.GestorTareas.business.domain.Task;
import es.gestor.GestorTareas.infrastructure.adapters.in.mapper.TaskInMapper;
import es.gestor.GestorTareas.infrastructure.adapters.out.mapper.TaskOutMapper;
import es.gestor.GestorTareas.infrastructure.dto.TaskRequest;
import es.gestor.GestorTareas.infrastructure.dto.TaskResponse;

@RestController
@RequestMapping("gestor/tareas")
public class TaskControllerAdapterIn {

    private CreateTaskUseCase createTaskUseCase;
    private UpdateTaskUseCase updateTaskUseCase;
    private DeleteTaskUseCase deleteTaskUseCase;
    private FindAllTaskUseCase findAllTaskUseCase;
    private FindTaskByIdUseCase findTaskByIdUseCase;
    private TaskInMapper taskInMapper;
    private TaskOutMapper taskOutMapper;

    public TaskControllerAdapterIn(CreateTaskUseCase createTaskUseCase, TaskInMapper taskInMapper,
            TaskOutMapper taskOutMapper, UpdateTaskUseCase updateTaskUseCase, DeleteTaskUseCase deleteTaskUseCase,
            FindAllTaskUseCase findAllTaskUseCase, FindTaskByIdUseCase findTaskByIdUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.taskInMapper = taskInMapper;
        this.taskOutMapper = taskOutMapper;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.findAllTaskUseCase = findAllTaskUseCase;
        this.findTaskByIdUseCase = findTaskByIdUseCase;
    }

    @PostMapping("/crear")
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest request) {
        Task task = createTaskUseCase.create(taskInMapper.toDomainDto(request));
        TaskResponse response = taskOutMapper.fromDomainResponse(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @RequestBody TaskRequest request) {
        Task updated = updateTaskUseCase.update(id, taskInMapper.toDomainDto(request));
        TaskResponse response = taskOutMapper.fromDomainResponse(updated);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        deleteTaskUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tarea borrada con éxito");

    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        List<Task> tasks = findAllTaskUseCase.findAll();
        List<TaskResponse> response = taskOutMapper.fromDomainResponse(tasks);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        Task task = findTaskByIdUseCase.findById(id);
        TaskResponse response = taskOutMapper.fromDomainResponse(task);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
