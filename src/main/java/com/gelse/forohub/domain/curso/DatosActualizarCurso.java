package com.gelse.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarCurso(
        @NotBlank String nombre,
        @NotBlank Categoria categoria
) {
}
