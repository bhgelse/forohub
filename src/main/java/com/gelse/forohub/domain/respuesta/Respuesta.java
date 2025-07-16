package com.gelse.forohub.domain.respuesta;

import com.gelse.forohub.domain.autor.Autor;
import com.gelse.forohub.domain.topico.Topico;

import java.time.LocalDateTime;

public class Respuesta {

    private Long id;
    private String mensaje;

    private Topico topico;
    private LocalDateTime fechaCreacion;

    private Autor autor;
    private String solucion;
}
