package com.gelse.forohub.controller;

import com.gelse.forohub.domain.topico.DatosRegistroTopico;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroTopico json){
        System.out.println(json);
    }
}
