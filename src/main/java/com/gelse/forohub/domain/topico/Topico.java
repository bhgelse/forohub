package com.gelse.forohub.domain.topico;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gelse.forohub.domain.usuario.Usuario;
import com.gelse.forohub.domain.curso.Curso;
import com.gelse.forohub.domain.respuesta.Respuesta;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean activo;
    private String titulo;

    @Column(length = 1000)
    private String mensaje;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico datos, Usuario autor, Curso curso) {
        this.id = null;
        this.activo = true;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.NO_RESPONDIDO;
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarInformaciones(@Valid DatosActualizarTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje= datos.mensaje();
        this.curso.actualizarInformaciones(datos.curso());
        this.status = Status.ACTUALIZADO;
        this.fechaCreacion = LocalDateTime.now();

    }

    public void eliminar() {
        this.activo = false;
        this.status = Status.CERRADO;
    }
}
