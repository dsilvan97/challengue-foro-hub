package com.biogade.foro_hub.domain.usuario;

import com.biogade.foro_hub.domain.perfil.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarUsuario(

        @NotBlank
        String nombre,

        @NotBlank
        String email,

        @NotBlank
        String clave,

        @NotNull
        Long perfil
) {
}
