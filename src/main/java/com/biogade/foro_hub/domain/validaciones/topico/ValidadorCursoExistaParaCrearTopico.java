package com.biogade.foro_hub.domain.validaciones.topico;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.curso.CursoRepository;
import com.biogade.foro_hub.domain.topico.DatosRegistrarTopico;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCursoExistaParaCrearTopico implements ValidadorDeTopico{

    private final CursoRepository repositorioCurso;

    public ValidadorCursoExistaParaCrearTopico(CursoRepository repositorioCurso) {
        this.repositorioCurso = repositorioCurso;
    }

    public void validar(DatosRegistrarTopico datos) {

        if (!repositorioCurso.existsById(datos.idCurso())){
            throw new ValidacionException("El curso ingresado no existe.");
        }

    }

}
