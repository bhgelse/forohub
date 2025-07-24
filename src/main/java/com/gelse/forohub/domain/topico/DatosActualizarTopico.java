package com.gelse.forohub.domain.topico;

import com.gelse.forohub.domain.curso.DatosActualizarCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull DatosActualizarCurso curso
) {
}
