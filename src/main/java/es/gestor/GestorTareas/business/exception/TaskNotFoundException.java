package es.gestor.GestorTareas.business.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Task no encontrada");
    }
}
