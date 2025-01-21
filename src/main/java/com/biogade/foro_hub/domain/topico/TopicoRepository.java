package com.biogade.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloEqualsIgnoreCaseAndMensajeEqualsIgnoreCase(String titulo, String mensaje);

}
