package br.com.neuranto.api.neuranto.security.impl;

import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.repository.UsuarioRepository;
import br.com.neuranto.api.neuranto.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Security;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var loginEmail = tokenService.validarToken(token);
            Usuario usuario = usuarioRepository.findByEmailUsuario(loginEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + loginEmail));

            DetalhesDoUsuario detalhesDoUsuario = new DetalhesDoUsuario(
                    usuario.getIdUsuario(),
                    usuario.getNomeUsuario(),
                    usuario.getEmailUsuario(),
                    usuario.getPassword(),
                    usuario.getRegraUsuario()
            );

            var authentication = new UsernamePasswordAuthenticationToken(detalhesDoUsuario, null, detalhesDoUsuario.getAuthorities());

            // Definir o contexto de segurança
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", ""); // Corrigido para "Bearer "
    }


}
