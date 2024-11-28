package br.com.neuranto.api.neuranto.services;

import br.com.neuranto.api.neuranto.entity.Comentario;
import br.com.neuranto.api.neuranto.entity.Postagem;
import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.entity.dto.ComentarioDTO;
import br.com.neuranto.api.neuranto.mapper.ComentarioMapper;
import br.com.neuranto.api.neuranto.repository.ComentarioRepository;
import br.com.neuranto.api.neuranto.repository.PostRepository;
import br.com.neuranto.api.neuranto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    ComentarioMapper comentarioMapper;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UsuarioRepository  usuarioRepository;

    @Autowired
    PostagemService postagemService;

    @Autowired
    UsuarioService  usuarioService;

    public ComentarioDTO salvarComentario(ComentarioDTO comentarioDTO){

        Optional<Usuario> usuario = usuarioRepository.findById(comentarioDTO.getUsuario());
        Optional<Postagem> postagem = postRepository.findById(comentarioDTO.getPostagem());

        Comentario comentario = comentarioMapper.converterParaEntidade(comentarioDTO);
        System.out.println("DTO para comentário: "+comentario.getUsuairo()+" , "+comentario.getPostagem());
        if ( usuario == null || usuario.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }
        if(postagem == null || postagem.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada!");
        }
            ComentarioDTO comentarioDTOConvertido = comentarioMapper.converterParaDto(comentarioRepository.saveAndFlush(comentario));
            return  comentarioDTOConvertido;
    }

    public HashMap<String, List<ComentarioDTO>> listarComentarios(){
        HashMap result = new HashMap<>();
        List<Comentario> comentarioList = comentarioRepository.findAll();
        if(comentarioList.isEmpty())
        {
            result.put("Nenhum comentário cadastrado aínda!", comentarioList);
        }
        if(comentarioList.isEmpty() == false)
        {
            result.put("Sucesso!", comentarioMapper.converterParaListaDeDtos(comentarioList));
        }

        return  result;
    }

    public List<ComentarioDTO> listarComentariosPorUsuario(Long idUsuario){
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        List<Comentario> comentarioList = comentarioRepository.findByUsuario(usuario);

        return comentarioMapper.converterParaListaDeDtos(comentarioList);
    }

    public List<ComentarioDTO> listarComentariosPorPostagem(Long idPostagem){
        Postagem postagem = postagemService.buscarPostagemPorId(idPostagem);

        List<Comentario> comentarioList = comentarioRepository.findByPostagem(postagem);

        return comentarioMapper.converterParaListaDeDtos(comentarioList);
    }

    public ComentarioDTO alterarComentario(ComentarioDTO comentarioDTO){
        Optional<Usuario> usuario = usuarioRepository.findById(comentarioDTO.getUsuario());
        Optional<Postagem> postagem = postRepository.findById(comentarioDTO.getPostagem());

        Comentario comentario = comentarioMapper.converterParaEntidade(comentarioDTO);

        if ( usuario == null || usuario.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }
        if(postagem == null || postagem.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada!");
        }

        ComentarioDTO comentarioDTOConvertido = comentarioMapper.converterParaDto(comentarioRepository.saveAndFlush(comentario));
        return  comentarioDTOConvertido;
    }

    public String excluirComentario(Long idComentario){
        Optional<Comentario> Comentario =
                Optional.ofNullable(comentarioRepository.findById(idComentario).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Comentario não encontrado!")));

        comentarioRepository.delete(Comentario.get());
        return("Comentario excluído com sucesso!");
    }
}
