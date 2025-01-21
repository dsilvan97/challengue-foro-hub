package com.biogade.foro_hub.domain.topico;

import com.biogade.foro_hub.domain.curso.Curso;
import com.biogade.foro_hub.domain.curso.CursoRepository;
import com.biogade.foro_hub.domain.usuario.Usuario;
import com.biogade.foro_hub.domain.usuario.UsuarioRepository;
import com.biogade.foro_hub.domain.validaciones.topico.ValidadorDeTopico;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository repositorio;
    private final UsuarioRepository repositorioUsuario;
    private final CursoRepository repositorioCurso;
    private final List<ValidadorDeTopico> validadores;

    public TopicoService(TopicoRepository repositorio, UsuarioRepository repositorioUsuario, CursoRepository repositorioCurso,
                         List<ValidadorDeTopico> validadores) {
        this.repositorio = repositorio;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioCurso = repositorioCurso;
        this.validadores = validadores;
    }

    public DatosCreadosTopico registrarTopico(DatosRegistrarTopico datos){

        //Ejecutar Validadores:
        //1. No permitir registros duplicados. (Con mismo título y mensaje)
        //2. Validar que el usuario ingresado exista.
        //3. Validar que el curso ingresado exista.
        validadores.forEach(v -> v.validar(datos));

        Usuario usuarioSeleccionado = repositorioUsuario.getReferenceById(datos.idAutor());
        Curso cursoSeleccionado = repositorioCurso.getReferenceById(datos.idCurso());

        Topico topicoNuevo = new Topico(null, datos.titulo(), datos.mensaje(), LocalDateTime.now(), true, usuarioSeleccionado, cursoSeleccionado, null);

        repositorio.save(topicoNuevo);

        return new DatosCreadosTopico(topicoNuevo.getId(), topicoNuevo.getTitulo(), topicoNuevo.getMensaje(),
                topicoNuevo.getFechaCreacion().toString(), topicoNuevo.getStatus().toString(), topicoNuevo.getUsuario().getNombre(),
                topicoNuevo.getCurso().getNombre());

    }

    public DatosCreadosTopico actualizarTopico(DatosActualizarTopico datos) {

        DatosRegistrarTopico datosParaVerificadores = new DatosRegistrarTopico(datos.titulo(), datos.mensaje(), datos.idAutor(), datos.idCurso());
        //Ejecutar Validadores:
        //1. No permitir registros duplicados. (Con mismo título y mensaje)
        //2. Validar que el usuario ingresado exista.
        //3. Validar que el curso ingresado exista.
        validadores.forEach(v -> v.validar(datosParaVerificadores));

        Topico topicoSeleccionado = repositorio.getReferenceById(datos.idTopico());
        Usuario usuarioSeleccionado = repositorioUsuario.getReferenceById(datos.idAutor());
        Curso cursoSeleccionado = repositorioCurso.getReferenceById(datos.idCurso());

        topicoSeleccionado.actualizarTopico(datos.titulo(), datos.mensaje(), usuarioSeleccionado, cursoSeleccionado);

        repositorio.save(topicoSeleccionado);

        return new DatosCreadosTopico(topicoSeleccionado.getId(), topicoSeleccionado.getTitulo(), topicoSeleccionado.getMensaje(),
                topicoSeleccionado.getFechaCreacion().toString(), topicoSeleccionado.getStatus().toString(), topicoSeleccionado.getUsuario().getNombre(),
                topicoSeleccionado.getCurso().getNombre());


    }
}
