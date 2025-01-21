package com.biogade.foro_hub.domain.validaciones.respuesta;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.respuesta.DatosRegistrarRespuesta;
import com.biogade.foro_hub.domain.topico.TopicoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoExistaParaCrearRespuesta implements ValidadorDeRespuesta{

    private final TopicoRepository repositorioTopico;

    public ValidadorTopicoExistaParaCrearRespuesta(TopicoRepository repositorioTopico) {
        this.repositorioTopico = repositorioTopico;
    }


    public void validar(DatosRegistrarRespuesta datos) {

        if (!repositorioTopico.existsById(datos.idTopico())){
            throw new ValidacionException("El tópico ingresado no existe.");
        }

    }

}
