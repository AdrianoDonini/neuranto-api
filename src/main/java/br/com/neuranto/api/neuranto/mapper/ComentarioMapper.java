package br.com.neuranto.api.neuranto.mapper;

import br.com.neuranto.api.neuranto.entity.Comentario;
import br.com.neuranto.api.neuranto.entity.Postagem;
import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.entity.dto.ComentarioDTO;
import br.com.neuranto.api.neuranto.services.PostagemService;
import br.com.neuranto.api.neuranto.services.UsuarioService;
import br.com.neuranto.api.neuranto.mapper.CustomObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComentarioMapper implements CustomObjectMapper<Comentario, ComentarioDTO> {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PostagemService postagemService;

    public ComentarioDTO converterParaDto(Comentario comentario){
        ComentarioDTO dto = new ComentarioDTO();

        dto.setIdComentario(comentario.getIdComentario());
        dto.setUsuario(comentario.getUsuairo().getIdUsuario());
        dto.setPostagem(comentario.getPostagem().getIdPostagem());
        dto.setComentario(comentario.getComentario());

        return dto;
    }

    public Comentario converterParaEntidade(ComentarioDTO dto){
        Comentario comentario = new Comentario();

        Postagem postagem1 = new Postagem();
        postagem1.setIdPostagem(dto.getPostagem());

        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(dto.getUsuario());
        comentario.setIdComentario(dto.getIdComentario());
        comentario.setUsuario(usuario1);
        comentario.setPostagem(postagem1);
        comentario.setComentario(dto.getComentario());
        return comentario;
    }

    public List<ComentarioDTO> converterParaListaDeDtos(List<Comentario> comentarioList){
        List<ComentarioDTO> dtoList = new ArrayList<>();
        for(Comentario comentario: comentarioList){
            dtoList.add(converterParaDto(comentario));
        }
        return dtoList;
    }

   }
