package com.biogade.foro_hub.domain.validaciones.curso;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.curso.Categoria;
import com.biogade.foro_hub.domain.curso.CursoRepository;
import com.biogade.foro_hub.domain.curso.DatosRegistrarCurso;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCategoriaExistenteParaCrearCurso implements ValidadorDeCurso{

    private final CursoRepository repositorio;

    public ValidadorCategoriaExistenteParaCrearCurso(CursoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void validar(DatosRegistrarCurso datos) {

        Boolean categoriaExistente = false;

        for (Categoria categoria : Categoria.values()){
            if (categoria.name().equalsIgnoreCase(datos.categoria())){
                categoriaExistente = true;
            }
        }

        if (!categoriaExistente){
            throw new ValidacionException("No existe la categoría ingresada.");
        }

    }

}
