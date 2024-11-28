package br.com.neuranto.api.neuranto.mapper;

import br.com.neuranto.api.neuranto.entity.Postagem;
import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.entity.dto.PostagemDTO;
import br.com.neuranto.api.neuranto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostagemMapper implements CustomObjectMapper<Postagem, PostagemDTO>{

    @Autowired
    UsuarioService usuarioService;

   public PostagemDTO converterParaDto(Postagem postagem){
        PostagemDTO postagemDTO = new PostagemDTO();
        postagemDTO.setIdPostagem(postagem.getIdPostagem());
        postagemDTO.setTituloPostagem(postagem.getTituloDaPostagem());
        postagemDTO.setConteudoTextoDaPostagem(postagem.getConteudoTextoDaPostagem());
        postagemDTO.setDescricao(postagem.getDescricaoDaPostagem());
        postagemDTO.setIdUsuario(postagem.getUsuario().getIdUsuario());
        return postagemDTO;
    }

    public Postagem converterParaEntidade(PostagemDTO dto){
       Postagem postagem = new Postagem();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
       postagem.setIdPostagem(dto.getIdPostagem());
       postagem.setTituloDaPostagem(dto.getTituloPostagem());
       postagem.setDescricaoDaPostagem(dto.getDescricao());
       postagem.setConteudoTextoDaPostagem(dto.getConteudoTextoDaPostagem());
       postagem.setUsuario(usuario);
       return postagem;
    }

    public List<PostagemDTO> converterParaListaDeDtos(List<Postagem> postagemList){
        List<PostagemDTO> list = new ArrayList<>();
        for (Postagem postagem : postagemList){
            list.add(converterParaDto(postagem));
        }
        return list;
        //return postagemList.stream().map((Postagem postagem)-> converterParaDto(postagem)).collect(Collectors.toList());
    }
}
