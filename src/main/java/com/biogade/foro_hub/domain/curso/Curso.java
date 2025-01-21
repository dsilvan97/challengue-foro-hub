package com.biogade.foro_hub.domain.curso;

import com.biogade.foro_hub.domain.topico.Topico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cursos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Topico> topicos;

    public void actualizarTopico(String nombre, String categoria) {

        this.nombre = nombre;
        this.categoria = Categoria.valueOf(categoria);

    }
}
