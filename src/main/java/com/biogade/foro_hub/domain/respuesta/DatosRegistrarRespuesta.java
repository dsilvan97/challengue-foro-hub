package com.biogade.foro_hub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;

public record DatosRegistrarRespuesta(

        @NotBlank
        String mensaje,

        @NotNull
        Long idTopico,

        @NotNull
        Long idAutor
) {
}
