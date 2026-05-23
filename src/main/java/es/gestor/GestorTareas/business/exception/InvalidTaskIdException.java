package es.gestor.GestorTareas.business.exception;

public class InvalidTaskIdException extends RuntimeException {
    public InvalidTaskIdException() {
        super("Id no puede ser nulo");
    }
}
