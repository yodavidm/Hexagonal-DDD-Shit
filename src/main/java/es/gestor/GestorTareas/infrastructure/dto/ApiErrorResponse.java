package es.gestor.GestorTareas.infrastructure.dto;

public record ApiErrorResponse(
        int status,
        String message,
        String path,
        String timestamp) {
}