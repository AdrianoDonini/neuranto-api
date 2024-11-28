package br.com.neuranto.api.neuranto.controller;

import br.com.neuranto.api.neuranto.entity.dto.ComentarioDTO;
import br.com.neuranto.api.neuranto.entity.dto.PostagemDTO;
import br.com.neuranto.api.neuranto.mapper.ComentarioMapper;
import br.com.neuranto.api.neuranto.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/comentario")
@CrossOrigin(value = "*")
public class ComentarioController {
    @Autowired
    ComentarioService comentarioService;

    @Autowired
    ComentarioMapper comentarioMapper;

    @PostMapping(value = "/salvar")
    public ResponseEntity<Object> salvarComentario(@RequestBody ComentarioDTO comentarioDTO){
        System.out.println("Comentário Controller: " + comentarioDTO.getUsuario());
         ComentarioDTO dto = comentarioService.salvarComentario(comentarioDTO);
        return  ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<HashMap<String, List<ComentarioDTO>>>listarComentario()
    {
        HashMap<String, List<ComentarioDTO>> comentarioDTOList = comentarioService.listarComentarios();
        return ResponseEntity.status(HttpStatus.OK).body(comentarioDTOList);
    }

    @GetMapping(value = "/listarPorUsuario/{idUsuario}")
    public ResponseEntity<Object> listarComentarioPorUsuario(@PathVariable Long idUsuario)
    {
        List<ComentarioDTO> comentarioDTOList = comentarioService.listarComentariosPorUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(comentarioDTOList);
    }

    @GetMapping(value = "/listarPorPostagem/{idPostagem}")
    public ResponseEntity<Object> listarComentarioPorPostagem(@PathVariable Long idPostagem)
    {
        List<ComentarioDTO> comentarioDTOList = comentarioService.listarComentariosPorPostagem(idPostagem);
        return ResponseEntity.status(HttpStatus.OK).body(comentarioDTOList);
    }

    @PutMapping(value = "alterarComentario")
    public ResponseEntity<Object> alterarComentario(@RequestBody ComentarioDTO comentarioDTO)
    {
        ComentarioDTO comentarioDTO1 = comentarioService.alterarComentario(comentarioDTO);
        return  ResponseEntity.status(HttpStatus.OK).body(comentarioDTO1);
    }

    @DeleteMapping(value = "excluirComentario/{idComentario}")
    public ResponseEntity<String> excluirComentario(@PathVariable Long idComentario)
    {
        return ResponseEntity.status(HttpStatus.OK).body(comentarioService.excluirComentario(idComentario));
    }
}
