package com.biogade.foro_hub.domain.validaciones.curso;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.curso.CursoRepository;
import com.biogade.foro_hub.domain.curso.DatosRegistrarCurso;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNombreCursoExistente implements ValidadorDeCurso{

    private final CursoRepository repositorio;

    public ValidadorNombreCursoExistente(CursoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void validar(DatosRegistrarCurso datos) {

        if (repositorio.existsByNombreEqualsIgnoreCase(datos.nombre())){
            throw new ValidacionException("Ya existe un curso creado con ese nombre.");
        }
        System.out.println("Pasó validador de nombre: " + datos.nombre());
        System.out.println("Dato de la BD: " + repositorio.existsByNombreEqualsIgnoreCase(datos.nombre()));

    }

}
