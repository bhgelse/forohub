package com.gelse.forohub.domain.topico;

import com.gelse.forohub.domain.usuario.DatosAutor;
import com.gelse.forohub.domain.curso.DatosCurso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull @Valid DatosAutor autor,
        @NotNull @Valid DatosCurso curso
) {
}
