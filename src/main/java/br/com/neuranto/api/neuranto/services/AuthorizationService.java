package br.com.neuranto.api.neuranto.services;

import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.repository.UsuarioRepository;
import br.com.neuranto.api.neuranto.security.impl.DetalhesDoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmailUsuario(emailUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + emailUsuario));

        // Instancia DetalhesDoUsuario a partir do Usuario
        return new DetalhesDoUsuario(
                usuario.getIdUsuario(),
                usuario.getNomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getPassword(),
                usuario.getRegraUsuario()
        );
    }
}
