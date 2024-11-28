package br.com.neuranto.api.neuranto.mapper;

import br.com.neuranto.api.neuranto.entity.UsuarioProfissional;
import br.com.neuranto.api.neuranto.entity.dto.UsuarioProfissionalDTO;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioProfissionalMapper implements CustomObjectMapper<UsuarioProfissional, UsuarioProfissionalDTO>{
    public UsuarioProfissionalDTO converterParaDto(UsuarioProfissional usuarioProfissional)
    {
        UsuarioProfissionalDTO usuarioProfissionalDTO = new UsuarioProfissionalDTO();
        usuarioProfissionalDTO.setIdUsuario(usuarioProfissional.getIdUsuario());
        usuarioProfissionalDTO.setNomeUsuario(usuarioProfissionalDTO.getNomeUsuario());
        usuarioProfissionalDTO.setEmailUsuario(usuarioProfissionalDTO.getEmailUsuario());
        usuarioProfissionalDTO.setSobreUsuario(usuarioProfissionalDTO.getSobreUsuario());
        usuarioProfissionalDTO.setCpf(usuarioProfissional.getCpf());
        usuarioProfissionalDTO.setCnpj(usuarioProfissional.getCnpj());
        usuarioProfissionalDTO.setCrp(usuarioProfissional.getCrp());
        usuarioProfissionalDTO.setValidarPerfil(usuarioProfissional.isValidarPerfil());

        return  usuarioProfissionalDTO;
    }

    public UsuarioProfissional converterParaEntidade(UsuarioProfissionalDTO usuarioProfissionalDTO)
    {
        UsuarioProfissional usuarioProfissional = new UsuarioProfissional();
        usuarioProfissional.setIdUsuario(usuarioProfissional.getIdUsuario());
        usuarioProfissional.setNomeUsuario(usuarioProfissionalDTO.getNomeUsuario());
        usuarioProfissional.setEmailUsuario(usuarioProfissionalDTO.getEmailUsuario());
        usuarioProfissional.setPassword(usuarioProfissionalDTO.getSenhaUsuario());
        usuarioProfissional.setSobreUsuario(usuarioProfissionalDTO.getSobreUsuario());
        usuarioProfissional.setCpf(usuarioProfissionalDTO.getCpf());
        usuarioProfissional.setCnpj(usuarioProfissionalDTO.getCnpj());
        usuarioProfissional.setCrp(usuarioProfissionalDTO.getCrp());
        usuarioProfissional.setValidarPerfil(usuarioProfissionalDTO.isValidarPerfil());

        return usuarioProfissional;
    }

    public List<UsuarioProfissionalDTO> converterParaListaDeDtos(List<UsuarioProfissional> usuarioProfissionalList)
    {
        List<UsuarioProfissionalDTO> usuarioProfissionalDTOList = new ArrayList<>();
        for(UsuarioProfissional usuarioProfissional : usuarioProfissionalList)
        {
            usuarioProfissionalDTOList.add(converterParaDto(usuarioProfissional));
        }

        return usuarioProfissionalDTOList;
    }
}
