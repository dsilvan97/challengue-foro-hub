package com.biogade.foro_hub.domain.validaciones.usuario;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.usuario.DatosRegistrarUsuario;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPerfilExistenteParaCrearUsuario implements ValidadorDeUsuario{

        public void validar(DatosRegistrarUsuario datos) {

            if (!(datos.perfil() >= 1 && datos.perfil()<=3)){
                throw new ValidacionException("El Perfil No Existe.");
            }
    }
}
