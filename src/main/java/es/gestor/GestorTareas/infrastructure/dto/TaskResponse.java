package es.gestor.GestorTareas.infrastructure.dto;

import java.time.LocalDate;

import es.gestor.GestorTareas.business.domain.PriorityEnum;
import es.gestor.GestorTareas.business.domain.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private Long id;

    private String title;

    private StatusEnum status;

    private PriorityEnum priority;

    private LocalDate dueDate;

}
