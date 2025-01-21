package com.biogade.foro_hub.domain.validaciones.respuesta;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.respuesta.DatosRegistrarRespuesta;
import com.biogade.foro_hub.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioExistaParaCrearRespuesta implements ValidadorDeRespuesta{

    private final UsuarioRepository repositorioUsuario;

    public ValidadorUsuarioExistaParaCrearRespuesta(UsuarioRepository repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }


    public void validar(DatosRegistrarRespuesta datos) {

        if (!repositorioUsuario.existsById(datos.idAutor())){
            throw new ValidacionException("El usuario ingresado no existe.");
        }

    }

}
