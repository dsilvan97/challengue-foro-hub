package com.biogade.foro_hub.domain.topico;

import com.biogade.foro_hub.domain.curso.Curso;
import com.biogade.foro_hub.domain.usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosCreadosTopico(

        Long id,

        String titulo,

        String mensaje,

        String fecha,

        String status,

        String autorTopico,

        String curso
) {
}
