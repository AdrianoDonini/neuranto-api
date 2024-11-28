package br.com.neuranto.api.neuranto.services;

import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario){return usuarioRepository.saveAndFlush(usuario);}

    public List<Usuario> listarUsuarios(){

        return  usuarioRepository.findAll();}

    public HashMap<String, Object> excluirUsuario(Long usuarioId)
    {
        Optional<Usuario> usuario = Optional.ofNullable(
            usuarioRepository.findById(usuarioId).
                    orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Usuário não encontrado!"))
        );
        usuarioRepository.delete(usuario.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Usuário: " +  usuario.get().getNomeUsuario() +  " excluído com sucesso!");
        return result;
    }
    public Usuario buscarUsuarioPorId(Long idUsuario) {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeDeUsuarioAutenticado = authentication.getName();
        Usuario usuarioPedido = usuarioRepository.findById(idUsuario).get();
        Usuario usuarioLogado = usuarioRepository.findByEmailUsuario(nomeDeUsuarioAutenticado).get();
        if(!usuarioPedido.equals(usuarioLogado))
        {
            throw   new ResponseStatusException(HttpStatus.FORBIDDEN,
                "Você não tem outorização!");
        }*/


       return usuarioRepository.findById(idUsuario)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário não encontrado!"));
    }

    public Usuario atualizarUsuario(Usuario usuario){
        if(buscarUsuarioPorId(usuario.getIdUsuario()) != null){
            return usuarioRepository.saveAndFlush(usuario);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }
    }

    public Usuario buscarUsuarioPorNome(String nomeUsuario){
        return (usuarioRepository.findByNomeUsuarioIgnoreCaseContaining(nomeUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Nenhum Usuário encontrado pelo nome informado: " + nomeUsuario)));

    }

    public HashMap<String, List<Usuario>> buscarUsuarioPorDataDeCriacao(LocalDate dataDeCriacao) {
        List<Usuario> listUsuarios = usuarioRepository.findByDataDeCriacao(dataDeCriacao);
        HashMap<String, List<Usuario>> result = new HashMap<>();

        if (listUsuarios.isEmpty()) {
            result.put("Nenhum usuáio encontrado pela data informada!", listUsuarios);
        }else {
            result.put("Sucesso", listUsuarios);
        }
        return result;
    }
}
