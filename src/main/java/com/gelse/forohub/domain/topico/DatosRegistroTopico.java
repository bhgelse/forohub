package com.gelse.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank(message = "El Topico debe tener un titulo")
        String titulo,
        @NotBlank(message = "El Topico debe tener un mensaje")
        String mensaje,
        @NotNull(message = "No existe un Autor para este Topico")
        Long idAutor,
        @NotNull(message = "No existe un Curso para este Topico")
        Long idCurso
) {
}
