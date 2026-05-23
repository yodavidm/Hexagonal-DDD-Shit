package es.gestor.GestorTareas.business.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 15, max = 35)
    private String title;

    @NotNull
    private StatusEnum status;

    @NotNull
    private PriorityEnum priority;

    private LocalDate dueDate;

    public void validateDueDate() {
        if (dueDate != null && LocalDate.now().isAfter(dueDate)) {
            throw new IllegalArgumentException("Fecha hasta no puede ser menor a la fecha actual");
        }
    }

    public void update(Task update) {
        checkStatusTransition(update.getStatus());

        this.title = update.getTitle();
        this.status = update.getStatus();
        this.priority = update.getPriority();
        this.dueDate = update.getDueDate();

        validateDueDate();

    }

    public void checkStatusTransition(StatusEnum newStatus) {

        if (this.status == StatusEnum.DONE) {
            throw new IllegalArgumentException("No puedes cambiar status DONE");
        }

        if (this.status == StatusEnum.IN_PROGRESS && newStatus.equals(StatusEnum.TODO)) {
            throw new IllegalArgumentException("No puedes pasar a TODO una tarea IN_PROGRESS");
        }

        if (this.status == StatusEnum.TODO && newStatus == StatusEnum.DONE) {
            throw new IllegalArgumentException("No puedes pasar de TODO a DONE directamente");
        }
    }

}
