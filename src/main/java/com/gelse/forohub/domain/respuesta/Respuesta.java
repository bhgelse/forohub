package com.gelse.forohub.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gelse.forohub.domain.usuario.Usuario;
import com.gelse.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta" )
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico")
    private Topico topico;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    private Boolean solucion;
}
