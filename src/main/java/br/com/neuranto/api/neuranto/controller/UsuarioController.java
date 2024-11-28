package br.com.neuranto.api.neuranto.controller;


import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/usuario")
@CrossOrigin(value = "*")
public class UsuarioController {
    @Autowired

    public UsuarioService usuarioService;

    @GetMapping(value = "/listar")
    public ResponseEntity<Object> listarUsuairos() {
        List<Usuario> listaUsuarios = usuarioService.listarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(listaUsuarios);
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<Object> salvarUsuario(@RequestBody Usuario Usuario){
        Usuario usuario = usuarioService.salvarUsuario(Usuario);
        return  ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PutMapping(value = "/alterar")
    public ResponseEntity<Object> alterarUsuario(@RequestBody Usuario Usuario){
        Usuario usuario = usuarioService.atualizarUsuario(Usuario);
        return  ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @DeleteMapping(value = "/excluir/{idUsuario}")
    public ResponseEntity<Object> excluirUsuario(@PathVariable Long idUsuario) {
        HashMap<String, Object> result = usuarioService.excluirUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/buscarUsuario/{idUsuario}")
    public ResponseEntity<Object> buscarUsuario(@PathVariable Long idUsuario){
        Usuario result = usuarioService.buscarUsuarioPorId(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
