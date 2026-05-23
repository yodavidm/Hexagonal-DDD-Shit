package es.gestor.GestorTareas.business.domain;

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
public class User {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 5, max = 15)
    private String username;

    @NotNull
    @Size(min = 5, max = 15)
    private String password;

}
