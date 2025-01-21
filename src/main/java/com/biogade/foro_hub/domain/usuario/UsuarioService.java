package com.biogade.foro_hub.domain.usuario;

import com.biogade.foro_hub.domain.perfil.Perfil;
import com.biogade.foro_hub.domain.perfil.PerfilRepository;
import com.biogade.foro_hub.domain.respuesta.DatosCreadosRespuesta;
import com.biogade.foro_hub.domain.validaciones.usuario.ValidadorDeUsuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    public final UsuarioRepository repositorio;
    public final List<ValidadorDeUsuario> validadores;
    public final PerfilRepository repositorioPerfil;

    public UsuarioService(UsuarioRepository repositorio, List<ValidadorDeUsuario> validadores, PerfilRepository repositorioPerfil){
        this.repositorio = repositorio;
        this. validadores = validadores;
        this.repositorioPerfil = repositorioPerfil;
    }

    public DatosCreadosUsuario registrarUsuario(DatosRegistrarUsuario datos) {

        //Verificar Validaciones.
        //1. No pueden existir 2 usuarios con el mismo nombre.
        //2. No pueden existir 2 usuarios con el mismo email.
        //3. El perfil seleccionado debe existir en la BD.
        validadores.forEach(v -> v.validar(datos));

        //El perfil existe, entonces se genera una instancia con el ID enviado.
        Perfil perfilEnviado = repositorioPerfil.getReferenceById(datos.perfil());

        //Ni el nombre ni el email enviado existe en la BD, por lo que se crea una instancia de Usuario,
        // a la cual se le envía los datos enviados por el cliente y el perfil anterior y se guarda en la BD.
        Usuario nuevoUsuario = new Usuario(null, datos.nombre(), datos.email(), datos.clave(), perfilEnviado, null, null);
        repositorio.save(nuevoUsuario);

        //Se crea una instancia del objeto creado en la BD para poder tomar el ID y usarlo al final.
        Usuario usuarioCreado = repositorio.findByEmailEquals(nuevoUsuario.getEmail());

        //Retorna un nuevo Record con los datos necesarios para enviar en el cuerpo de respuesta al cliente.
        return new DatosCreadosUsuario(usuarioCreado.getId(), usuarioCreado.getNombre(), usuarioCreado.getEmail(), usuarioCreado.getPerfil().getNombrePerfil());
    }


    public DatosCreadosUsuario actualizarTopico(DatosActualizarUsuario datos) {

        DatosRegistrarUsuario datosParaVerificadores = new DatosRegistrarUsuario(datos.nombre(), datos.email(), datos.clave(), datos.perfil());
        //Ejecutar Validaciones.
        //1. No pueden existir 2 usuarios con el mismo nombre.
        //2. No pueden existir 2 usuarios con el mismo email.
        //3. El perfil seleccionado debe existir en la BD.
        validadores.forEach(v -> v.validar(datosParaVerificadores));

        Usuario usuarioSeleccionado = repositorio.getReferenceById(datos.idUsuario());

        usuarioSeleccionado.actualizarRespuesta(datos.nombre(), datos.email(), datos.clave(), repositorioPerfil.getReferenceById(datos.perfil()));

        repositorio.save(usuarioSeleccionado);

        return new DatosCreadosUsuario(usuarioSeleccionado.getId(), usuarioSeleccionado.getNombre(), usuarioSeleccionado.getEmail(),
                usuarioSeleccionado.getPerfil().getNombrePerfil());

    }

}
