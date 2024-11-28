package br.com.neuranto.api.neuranto.controller;

import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.entity.dto.PostagemDTO;
import br.com.neuranto.api.neuranto.services.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/api/v1/postagem")
@CrossOrigin(value = "*")
public class PostagemController {
    @Autowired
    public PostagemService postagemService;

    @GetMapping(value = "/listar")
    public ResponseEntity<Object> listarPostagem() {
        List<PostagemDTO> listaPostagem = postagemService.listarPostagem();
        return ResponseEntity.status(HttpStatus.OK).body(listaPostagem);
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<Object> salvarPostagem(@RequestBody PostagemDTO postagemDTO){
        PostagemDTO postagem = postagemService.salvarPostagem(postagemDTO, postagemDTO.getIdUsuario());
        return  ResponseEntity.status(HttpStatus.OK).body(postagem);
    }

    @PutMapping(value = "/alterar")
    public ResponseEntity<Object> alterarPostagem(@RequestBody PostagemDTO postagemDTO){
        PostagemDTO postagem = postagemService.atualizarPostagem(postagemDTO, postagemDTO.getIdUsuario());
        return  ResponseEntity.status(HttpStatus.OK).body(postagem);
    }

    @DeleteMapping(value = "/excluir/{idPostagem}")
    public ResponseEntity<Object> excluirPostagem(@PathVariable Long idPostagem) {
        HashMap<String, Object> result = postagemService.excluirPostagem(idPostagem);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/buscarPostagemPorUsuario/{idUsuario}")
    public ResponseEntity<Object> buscarPostagemPorUsuario(@PathVariable Long idUsuario){
        List<PostagemDTO> result = postagemService.listarPostagemPorIdUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
