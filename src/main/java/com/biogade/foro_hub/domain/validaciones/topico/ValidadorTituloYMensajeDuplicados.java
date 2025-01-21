package com.biogade.foro_hub.domain.validaciones.topico;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.topico.DatosRegistrarTopico;
import com.biogade.foro_hub.domain.topico.TopicoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTituloYMensajeDuplicados implements ValidadorDeTopico{

    private final TopicoRepository repositorio;

    public ValidadorTituloYMensajeDuplicados(TopicoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void validar(DatosRegistrarTopico datos){

        if(repositorio.existsByTituloEqualsIgnoreCaseAndMensajeEqualsIgnoreCase(datos.titulo(), datos.mensaje())){
           throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje.");
        }

    }

}
