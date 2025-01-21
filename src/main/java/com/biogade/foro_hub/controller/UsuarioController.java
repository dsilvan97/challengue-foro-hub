package com.biogade.foro_hub.controller;

import com.biogade.foro_hub.domain.ValidacionException;
import com.biogade.foro_hub.domain.respuesta.DatosActualizarRespuesta;
import com.biogade.foro_hub.domain.respuesta.DatosCreadosRespuesta;
import com.biogade.foro_hub.domain.respuesta.DatosRegistrarRespuesta;
import com.biogade.foro_hub.domain.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {


    private final UsuarioService service;
    private final UsuarioRepository repositorio;

    public UsuarioController(UsuarioService service, UsuarioRepository repositorio){
        this.service = service;
        this.repositorio = repositorio;
    }

    @PostMapping
    public ResponseEntity<DatosCreadosUsuario> registrarUsuario(@RequestBody @Valid DatosRegistrarUsuario datos,
                                                                UriComponentsBuilder uriComponentsBuilder){

        DatosCreadosUsuario detalleUsuario = service.registrarUsuario(datos);

        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(detalleUsuario.id()).toUri();

        return ResponseEntity.created(url).body(detalleUsuario);

    }

    @GetMapping
    public ResponseEntity<List<DatosCreadosUsuario>> listarUsuarios(){

        List<DatosCreadosUsuario> listaUsuariosCreados = repositorio.findAll().stream()
                .map(u -> new DatosCreadosUsuario(u.getId(), u.getNombre(), u.getEmail(), u.getPerfil().getNombrePerfil()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaUsuariosCreados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosCreadosUsuario> listarUsuarioEspecifico(@PathVariable Long id){

        if (!repositorio.existsById(id)){
            throw new ValidacionException("El usuario a buscar no existe.");
        }

        Usuario usuarioEncontrado = repositorio.getReferenceById(id);

        DatosCreadosUsuario usuarioSeleccionado = new DatosCreadosUsuario(usuarioEncontrado.getId(), usuarioEncontrado.getNombre(),
                usuarioEncontrado.getEmail(), usuarioEncontrado.getPerfil().getNombrePerfil());

        return ResponseEntity.ok(usuarioSeleccionado);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosCreadosUsuario> actualizarUsuario(@PathVariable Long id,
                                                                     @RequestBody @Valid DatosRegistrarUsuario datos,
                                                                     UriComponentsBuilder uriComponentsBuilder){


        if (!repositorio.existsById(id)){
            throw new ValidacionException("El Usuario a actualizar no existe.");
        }

        DatosActualizarUsuario datosParaActualizar = new DatosActualizarUsuario(id, datos.nombre(), datos.email(), datos.clave(), datos.perfil());

        DatosCreadosUsuario usuarioActualizado = service.actualizarTopico(datosParaActualizar);

        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioActualizado.id()).toUri();

        return ResponseEntity.ok(usuarioActualizado);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){

        if (!repositorio.existsById(id)){
            throw new ValidacionException("El usuario a eliminar no existe.");
        }

        repositorio.deleteById(id);

        return ResponseEntity.noContent().build();

    }


}
