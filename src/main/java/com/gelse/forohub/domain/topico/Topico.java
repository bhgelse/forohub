package com.gelse.forohub.domain.topico;


import com.gelse.forohub.domain.autor.Autor;
import com.gelse.forohub.domain.curso.Curso;
import com.gelse.forohub.domain.respuesta.Respuesta;

import java.time.LocalDateTime;

public class Topico {
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;

    private Autor autor;

    private Curso curso;

    private Respuesta respuestas;
}
