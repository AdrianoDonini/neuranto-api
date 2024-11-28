package br.com.neuranto.api.neuranto.controller;

import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.entity.dto.ComentarioDTO;
import br.com.neuranto.api.neuranto.entity.dto.LoginDTO;
import br.com.neuranto.api.neuranto.entity.dto.LoginResponseDTO;
import br.com.neuranto.api.neuranto.entity.dto.RegistrarDTO;
import br.com.neuranto.api.neuranto.repository.UsuarioRepository;
import br.com.neuranto.api.neuranto.security.impl.DetalhesDoUsuario;
import br.com.neuranto.api.neuranto.security.impl.SecurityFilter;
import br.com.neuranto.api.neuranto.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO)
    {
        var usuarioSenha = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());
        var auth = this.authenticationManager.authenticate(usuarioSenha);

        var token = tokenService.gerarToken((DetalhesDoUsuario) auth.getPrincipal());

        return  ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Object> regitrar (@RequestBody @Valid RegistrarDTO registrarDTO)
    {
        if(this.usuarioRepository.findByEmailUsuario(registrarDTO.getEmail()).isPresent())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"E esse e-mail ja está sendo usado por outro usuário!");
        }

        String encriptedSenha = new BCryptPasswordEncoder().encode(registrarDTO.getSenha());

        Usuario novoUsuario = new Usuario(registrarDTO.getNome(), registrarDTO.getEmail(), encriptedSenha, registrarDTO.getRegra());

        this.usuarioRepository.saveAndFlush(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}
