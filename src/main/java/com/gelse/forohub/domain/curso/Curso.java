package com.gelse.forohub.domain.curso;

import com.gelse.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos;

    public void actualizarInformaciones(@Valid DatosActualizarCurso datos) {
            this.nombre = datos.nombre();
            this.categoria = datos.categoria();
    }
}
