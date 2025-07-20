package com.gelse.forohub.controller;

import com.gelse.forohub.domain.curso.Curso;
import com.gelse.forohub.domain.curso.CursoRepository;
import com.gelse.forohub.domain.topico.*;
import com.gelse.forohub.domain.usuario.Usuario;
import com.gelse.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody @Valid DatosRegistroTopico datos){
        if(topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            return ResponseEntity.badRequest().body("Ya existe un topico con el mismo Titulo y Mensaje");
        }

        Usuario autor = usuarioRepository.findById(datos.idAutor())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        Curso curso = cursoRepository.findById(datos.idCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Topico topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @GetMapping
    public Page<DatosRespuestaTopico> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detallar(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> Actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if(topicoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Topico topico = topicoOptional.get();

        boolean duplicado = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje()) &&
                topico.getTitulo().equals(datos.titulo()) || topico.getMensaje().equals(datos.mensaje());

        if(duplicado){
            return ResponseEntity.badRequest().body("Ya existe un topico con el mismo titulo y mensaje");
        }

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.getCurso().setNombre(datos.curso());
        topico.setStatus(Status.ACTUALIZADO);
        topico.setFechaCreacion(LocalDateTime.now());

        topicoRepository.save(topico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }
}
