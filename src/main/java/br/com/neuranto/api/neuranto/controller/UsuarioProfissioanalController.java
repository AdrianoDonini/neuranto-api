package br.com.neuranto.api.neuranto.controller;

import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.entity.UsuarioProfissional;
import br.com.neuranto.api.neuranto.entity.dto.ConverterParaProfissionalDTO;
import br.com.neuranto.api.neuranto.entity.dto.UsuarioProfissionalDTO;
import br.com.neuranto.api.neuranto.services.UsuarioProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/usuarioProfissional")
@CrossOrigin(value = "*")

public class UsuarioProfissioanalController {

    @Autowired
    UsuarioProfissionalService usuarioProfissionalService;

    @PostMapping(value = "/salvar")
    public ResponseEntity<Object> salvarUsuarioProfissional(@RequestBody UsuarioProfissionalDTO usuarioProfissionalDTO){
        UsuarioProfissionalDTO usuarioProfissional1 = usuarioProfissionalService.salvarUsuarioProfissional(usuarioProfissionalDTO);
        return  ResponseEntity.status(HttpStatus.OK).body(usuarioProfissional1);
    }

    @PostMapping(value = "/tornarProfissional")
    public ResponseEntity<Object> converterParaProfissional(@RequestBody ConverterParaProfissionalDTO converterParaProfissionalDTO)
    {
        UsuarioProfissionalDTO usuarioProfissionalDTO = usuarioProfissionalService.alterarParaProfissional(converterParaProfissionalDTO);
        return  ResponseEntity.status(HttpStatus.OK).body(usuarioProfissionalDTO);
    }

    @PostMapping(value = "/verificarProfissional/{idProfissional}")
    public ResponseEntity<Object> validarProfissional(@PathVariable Long idProfissional)
    {
        String retorno = usuarioProfissionalService.validarProfissional(idProfissional);
        return ResponseEntity.status(HttpStatus.OK).body(retorno);
    }

    @PutMapping(value = "/alterarProfissional")
    public  ResponseEntity<Object> alterarProfissional(@RequestBody UsuarioProfissionalDTO usuarioProfissionalDTO)
    {
        UsuarioProfissionalDTO usuarioProfissionalDTO1 = usuarioProfissionalService.alterarUsuarioProfissional(usuarioProfissionalDTO);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioProfissionalDTO1);
    }
}
