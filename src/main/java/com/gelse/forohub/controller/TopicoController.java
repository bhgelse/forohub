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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

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
}
