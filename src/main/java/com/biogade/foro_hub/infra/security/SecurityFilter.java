package com.biogade.foro_hub.infra.security;

import com.biogade.foro_hub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    private static final List<String> EXCLUDED_PATHS = List.of(
            "/login",
            "/v3/api-docs",
            "/swagger-ui.html",
            "/swagger-ui"
    );

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Excluir rutas específicas
        String requestPath = request.getServletPath();

        if (EXCLUDED_PATHS.stream().anyMatch(requestPath::contains)) {
            filterChain.doFilter(request, response);
            return;
        }

        //Obtener El Token del Header:
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null){
            var token = authHeader.replace("Bearer", "").replace(" ", "");
            var subject = tokenService.getSubject(token);

            if (subject != null){
                //Token Válido...
                var usuario = usuarioRepository.findByNombre(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }else {
            throw new RuntimeException("El token enviado no es válido...");
        }

    }
}