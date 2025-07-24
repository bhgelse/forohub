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
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {

        Usuario autor = usuarioRepository.getReferenceById(datos.idAutor());
        Curso curso = cursoRepository.getReferenceById(datos.idCurso());

        Topico topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        var page = topicoRepository.findAllByActivoTrue(paginacion).map(DatosRespuestaTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallar(@PathVariable Long id) {
       Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity Actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = topicoRepository.getReferenceById(id);

        topico.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}/cerrar")
    public ResponseEntity cerrar(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);

        topico.eliminar();
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
