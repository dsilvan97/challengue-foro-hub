package com.biogade.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(

        @NotNull
        Long idTopico,

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotNull
        Long idAutor,

        @NotNull
        Long idCurso


) {
}
