package br.com.neuranto.api.neuranto.services;

import br.com.neuranto.api.neuranto.entity.Postagem;
import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.entity.dto.PostagemDTO;
import br.com.neuranto.api.neuranto.repository.PostRepository;
import br.com.neuranto.api.neuranto.services.UsuarioService;
import br.com.neuranto.api.neuranto.mapper.PostagemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {
    @Autowired
    private PostRepository postagemRepository;

    @Autowired
    private PostagemMapper postagemMapper;

    @Autowired
    private UsuarioService usuarioService;

    public PostagemDTO salvarPostagem(PostagemDTO postagemDTO, Long idUsuario) {

        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Postagem postagem = new Postagem();
        postagem.setTituloDaPostagem(postagemDTO.getTituloPostagem());
        postagem.setDescricaoDaPostagem(postagemDTO.getDescricao());
        postagem.setConteudoTextoDaPostagem(postagemDTO.getConteudoTextoDaPostagem());
        postagem.setUsuario(usuario);

        return postagemMapper.converterParaDto(postagemRepository.saveAndFlush(postagem));
    }

    public List<PostagemDTO> listarPostagem(){
       return postagemMapper.converterParaListaDeDtos(postagemRepository.findAll());
    }

    public List<PostagemDTO> listarPostagemPorIdUsuario(Long idUsuario){
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        return postagemMapper.converterParaListaDeDtos(postagemRepository.findByUsuario(usuario));
    }

    public Postagem buscarPostagemPorId(Long idPostagem){
        return postagemRepository.findById(idPostagem).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Postagem não encontrada!"));

    }

    public PostagemDTO atualizarPostagem(PostagemDTO postagemDTO, Long idUsuario){
        Postagem postagem = new Postagem();
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        postagem.setUsuario(usuario);
        postagem.setIdPostagem(postagemDTO.getIdPostagem());
        postagem.setDescricaoDaPostagem(postagemDTO.getDescricao());
        postagem.setTituloDaPostagem(postagemDTO.getTituloPostagem());
        postagem.setConteudoTextoDaPostagem(postagemDTO.getConteudoTextoDaPostagem());

        if(postagemRepository.findById(postagemDTO.getIdPostagem()) != null){
            return postagemMapper.converterParaDto(postagemRepository.saveAndFlush(postagem));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada!");
        }
    }

    public HashMap<String, Object> excluirPostagem(Long idPostagem){
        Optional<Postagem> Postagem =
                Optional.ofNullable(postagemRepository.findById(idPostagem).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Postagem não encontrada!")));


        postagemRepository.delete(Postagem.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Postagem: " +  Postagem.get().getTituloDaPostagem() +  " excluída com sucesso!");
        return result;
    }

}
