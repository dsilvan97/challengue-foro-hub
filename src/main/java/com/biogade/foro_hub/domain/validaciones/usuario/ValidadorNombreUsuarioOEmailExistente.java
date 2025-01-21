package com.biogade.foro_hub.domain.validaciones.usuario;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.usuario.DatosRegistrarUsuario;
import com.biogade.foro_hub.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNombreUsuarioOEmailExistente implements ValidadorDeUsuario{

    public final UsuarioRepository repositorio;

    public ValidadorNombreUsuarioOEmailExistente(UsuarioRepository repositorio){
        this.repositorio = repositorio;
    }

    public void validar(DatosRegistrarUsuario datos){

        Boolean nombreUsuarioRegistrado = repositorio.existsByNombreEqualsIgnoreCase(datos.nombre());

        Boolean emailUsuarioRegistrado = repositorio.existsByEmailEqualsIgnoreCase(datos.email());

        if(nombreUsuarioRegistrado || emailUsuarioRegistrado){
            throw new ValidacionException("El nombre de usuario o correo ya se encuentra registrado en la base de datos.");
        }

    }


}
