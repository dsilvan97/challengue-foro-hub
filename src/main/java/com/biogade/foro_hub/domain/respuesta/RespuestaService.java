package com.biogade.foro_hub.domain.respuesta;

import com.biogade.foro_hub.domain.curso.Curso;
import com.biogade.foro_hub.domain.topico.DatosCreadosTopico;
import com.biogade.foro_hub.domain.topico.DatosRegistrarTopico;
import com.biogade.foro_hub.domain.topico.Topico;
import com.biogade.foro_hub.domain.topico.TopicoRepository;
import com.biogade.foro_hub.domain.usuario.Usuario;
import com.biogade.foro_hub.domain.usuario.UsuarioRepository;
import com.biogade.foro_hub.domain.validaciones.respuesta.ValidadorDeRespuesta;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaService {

    private final RespuestaRepository repositorio;
    private final UsuarioRepository repositorioUsuario;
    private final TopicoRepository repositorioTopico;
    private final List<ValidadorDeRespuesta> validadores;

    public RespuestaService(RespuestaRepository repositorio, UsuarioRepository repositorioUsuario,
                            TopicoRepository repositorioTopico, List<ValidadorDeRespuesta> validadores) {
        this.repositorio = repositorio;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioTopico = repositorioTopico;
        this.validadores = validadores;
    }


    public DatosCreadosRespuesta registrarRespuesta(@Valid DatosRegistrarRespuesta datos) {

        //Ejecutar Validadores:
        //1. El tópico ingresado debe existir.
        //2. El usuario ingresado debe existir.
        validadores.forEach(v -> v.validar(datos));

        Topico topicoSeleccionado = repositorioTopico.getReferenceById(datos.idTopico());
        Usuario usuarioSeleccionado = repositorioUsuario.getReferenceById(datos.idAutor());

        Respuesta nuevaRespuesta = new Respuesta(null, datos.mensaje(), topicoSeleccionado, LocalDateTime.now(), usuarioSeleccionado, false);
        repositorio.save(nuevaRespuesta);

        return new DatosCreadosRespuesta(nuevaRespuesta.getId(), nuevaRespuesta.getMensaje(), nuevaRespuesta.getTopico().getTitulo(),
                nuevaRespuesta.getFechaRespuesta().toString(), nuevaRespuesta.getUsuario().getNombre());
    }

    public DatosCreadosRespuesta actualizarTopico(DatosActualizarRespuesta datos) {

        DatosRegistrarRespuesta datosParaVerificadores = new DatosRegistrarRespuesta(datos.mensaje(), datos.idTopico(), datos.idAutor());
        //Ejecutar Validadores:
        //1. El tópico ingresado debe existir.
        //2. El usuario ingresado debe existir.
        validadores.forEach(v -> v.validar(datosParaVerificadores));

        Topico topicoSeleccionado = repositorioTopico.getReferenceById(datos.idTopico());
        Usuario usuarioSeleccionado = repositorioUsuario.getReferenceById(datos.idAutor());
        Respuesta respuestaSeleccionada = repositorio.getReferenceById(datos.idRespuesta());

        respuestaSeleccionada.actualizarRespuesta(datos.mensaje(), topicoSeleccionado, usuarioSeleccionado);

        repositorio.save(respuestaSeleccionada);

        return new DatosCreadosRespuesta(respuestaSeleccionada.getId(), respuestaSeleccionada.getMensaje(), respuestaSeleccionada.getTopico().getTitulo(),
                respuestaSeleccionada.getFechaRespuesta().toString(), respuestaSeleccionada.getUsuario().getNombre());


    }
}
