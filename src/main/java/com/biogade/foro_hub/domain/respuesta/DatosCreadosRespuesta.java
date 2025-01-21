package com.biogade.foro_hub.domain.respuesta;

public record DatosCreadosRespuesta(

        Long id,
        String mensaje,
        String topico,
        String fechaCreacion,
        String autorRespuesta
) {
}
