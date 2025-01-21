package com.biogade.foro_hub.domain.validaciones.topico;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.topico.DatosRegistrarTopico;
import com.biogade.foro_hub.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioExistaParaCrearTopico implements ValidadorDeTopico{

    private final UsuarioRepository repositorioUsuario;

    public ValidadorUsuarioExistaParaCrearTopico(UsuarioRepository repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void validar(DatosRegistrarTopico datos) {

        if (!repositorioUsuario.existsById(datos.idAutor())){
            throw new ValidacionException("El usuario ingresado no existe.");
        }

    }

}
