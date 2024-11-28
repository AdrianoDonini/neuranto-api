package br.com.neuranto.api.neuranto.services;

import br.com.neuranto.api.neuranto.Enumerated.RegraUsuario;
import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.entity.UsuarioProfissional;
import br.com.neuranto.api.neuranto.entity.dto.ConverterParaProfissionalDTO;
import br.com.neuranto.api.neuranto.entity.dto.UsuarioProfissionalDTO;
import br.com.neuranto.api.neuranto.mapper.UsuarioProfissionalMapper;
import br.com.neuranto.api.neuranto.repository.UsuarioProfissionalRepository;
import br.com.neuranto.api.neuranto.repository.UsuarioRepository;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioProfissionalService {
    @Autowired
    UsuarioProfissionalRepository usuarioProfissionalRepository;

    @Autowired
    UsuarioProfissionalMapper usuarioProfissionalMapper;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;



    public UsuarioProfissionalDTO salvarUsuarioProfissional (UsuarioProfissionalDTO usuarioProfissionalDTO)
    {
        UsuarioProfissional usuarioProfissional;
        usuarioProfissional = usuarioProfissionalMapper.converterParaEntidade(usuarioProfissionalDTO);
        return  usuarioProfissionalMapper.converterParaDto(usuarioProfissionalRepository.saveAndFlush(usuarioProfissional));
    }



    public  UsuarioProfissionalDTO alterarUsuarioProfissional (UsuarioProfissionalDTO usuarioProfissionalDTO)
    {
        UsuarioProfissional usuarioProfissional = usuarioProfissionalMapper.converterParaEntidade(usuarioProfissionalDTO);

        //Verifica se u usuário que está tentando alterar é o que está logado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeDeUsuarioAutenticado = authentication.getName();
        Usuario usuarioPedido = usuarioRepository.findById(usuarioProfissional.getIdUsuario()).get();
        Usuario usuarioLogado = usuarioRepository.findByEmailUsuario(nomeDeUsuarioAutenticado).get();
        if(!usuarioPedido.equals(usuarioLogado))
        {
            throw   new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Você não tem outorização!");
        }

        if(usuarioService.buscarUsuarioPorId(usuarioProfissional.getIdUsuario()) != null){
            return usuarioProfissionalMapper.converterParaDto(usuarioProfissionalRepository.saveAndFlush(usuarioProfissional));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }
    }

    public  UsuarioProfissionalDTO alterarParaProfissional(ConverterParaProfissionalDTO converterParaProfissionalDTO)
    {
        //Pega o usuario logado para buscar os atributos do usuario para murdar para profissioanl
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeDeUsuarioAutenticado = authentication.getName();
        Usuario usuarioLogado = usuarioRepository.findByEmailUsuario(nomeDeUsuarioAutenticado).get();

        UsuarioProfissional usuarioProfissional = new UsuarioProfissional();
        System.out.println(usuarioLogado.getNomeUsuario());
        usuarioProfissional.setNomeUsuario(usuarioLogado.getNomeUsuario());
        usuarioProfissional.setSobreUsuario(usuarioLogado.getSobreUsuario());
        usuarioProfissional.setEmailUsuario(usuarioLogado.getEmailUsuario());
        usuarioProfissional.setPassword(usuarioLogado.getPassword());
        usuarioProfissional.setCrp(converterParaProfissionalDTO.getCrp());
        usuarioProfissional.setCpf(converterParaProfissionalDTO.getCpf());
        usuarioProfissional.setCnpj(converterParaProfissionalDTO.getCnpj());
        usuarioProfissional.setValidarPerfil(false);
        usuarioProfissional.setRegraUsuario(RegraUsuario.PROFISSIONAL);

        usuarioService.excluirUsuario(usuarioLogado.getIdUsuario());

        usuarioProfissionalRepository.saveAndFlush(usuarioProfissional);

        return usuarioProfissionalMapper.converterParaDto(usuarioProfissional);
    }

    public UsuarioProfissional buscarProfissionalPorId(Long id){
        return usuarioProfissionalRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Produto não encontrada!"));
    }

    public String validarProfissional(Long id)
    {
        UsuarioProfissional usuarioProfissional = this.buscarProfissionalPorId(id);
        usuarioProfissional.setValidarPerfil(true);

        usuarioProfissionalRepository.saveAndFlush(usuarioProfissional);

        return "Profissional: "+ usuarioProfissional.getNomeUsuario()+" Veridicado com sucesso!";
    }
}
