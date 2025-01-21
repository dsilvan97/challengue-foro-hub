package com.biogade.foro_hub.domain.respuesta;

import com.biogade.foro_hub.domain.topico.Topico;
import com.biogade.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne
    private Topico topico;

    @DateTimeFormat
    private LocalDateTime fechaRespuesta;

    @ManyToOne
    private Usuario usuario;

    private Boolean solucion;

    public void actualizarRespuesta(String mensaje, Topico topicoSeleccionado, Usuario usuarioSeleccionado) {

        this.mensaje = mensaje;
        this.topico = topicoSeleccionado;
        this.usuario = usuarioSeleccionado;

    }
}
