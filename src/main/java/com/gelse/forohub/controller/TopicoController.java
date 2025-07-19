package com.gelse.forohub.controller;

import com.gelse.forohub.domain.curso.Curso;
import com.gelse.forohub.domain.curso.CursoRepository;
import com.gelse.forohub.domain.topico.DatosRespuestaTopico;
import com.gelse.forohub.domain.topico.DatosRegistroTopico;
import com.gelse.forohub.domain.topico.Topico;
import com.gelse.forohub.domain.topico.TopicoRepository;
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

import java.util.List;

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

        Usuario autor = usuarioRepository.getReferenceById(datos.idAutor());
        Curso curso = cursoRepository.getReferenceById(datos.idCurso());

        Topico topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @GetMapping
    public Page<DatosRespuestaTopico> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new);
    }

    @GetMapping("/{id}")
    public DatosRespuestaTopico detallar(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        return new DatosRespuestaTopico(topico);
    }
}
