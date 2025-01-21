package com.biogade.foro_hub.domain.usuario;

import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByNombreEqualsIgnoreCase(String nombre);

    Boolean existsByEmailEqualsIgnoreCase(String email);

    Usuario findByEmailEquals(String email);

    UserDetails findByNombre(String username);
}
