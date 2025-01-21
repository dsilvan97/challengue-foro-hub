package com.biogade.foro_hub.domain.perfil;

import com.biogade.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "perfiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePerfil;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios;
}
