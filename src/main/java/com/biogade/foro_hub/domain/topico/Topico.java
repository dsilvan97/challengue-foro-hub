package com.biogade.foro_hub.domain.topico;

import com.biogade.foro_hub.domain.curso.Curso;
import com.biogade.foro_hub.domain.respuesta.Respuesta;
import com.biogade.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    @DateTimeFormat
    private LocalDateTime fechaCreacion;

    private Boolean status;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas;

    //******************************************************************************************************************

    public void actualizarTopico(String titulo, String mensaje, Usuario usuario, Curso curso) {

        this.titulo = titulo;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.curso = curso;

    }

}