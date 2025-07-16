package com.gelse.forohub.domain.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutor(
        @NotBlank String nombre,
        @NotBlank @Email String correoElectronico
) {
}
