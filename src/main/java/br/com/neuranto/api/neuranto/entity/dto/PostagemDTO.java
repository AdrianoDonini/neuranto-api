package br.com.neuranto.api.neuranto.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public class PostagemDTO {

    private Long idPostagem;
    private Long idUsuario;

    @NotNull(message = "O título precisa ser informado!")
    @NotBlank(message = "O título não pode estar em branco")
    private String tituloPostagem;

    private String descricao;

    @NotNull(message = "O texto do conteúdo precisa ser informado!")
    @NotBlank(message = "O conteúdo não pode estar em branco!")
    private String conteudoTextoDaPostagem;

    public PostagemDTO(Long idPostagem) {
        this.idPostagem = idPostagem;
    }

    public PostagemDTO() {
    }

    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public @NotNull(message = "O título precisa ser informado!") @NotBlank(message = "O título não pode estar em branco") String getTituloPostagem() {
        return tituloPostagem;
    }

    public void setTituloPostagem(@NotNull(message = "O título precisa ser informado!") @NotBlank(message = "O título não pode estar em branco") String tituloPostagem) {
        this.tituloPostagem = tituloPostagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O texto do conteúdo precisa ser informado!") @NotBlank(message = "O conteúdo não pode estar em branco!") String getConteudoTextoDaPostagem() {
        return conteudoTextoDaPostagem;
    }

    public void setConteudoTextoDaPostagem(@NotNull(message = "O texto do conteúdo precisa ser informado!") @NotBlank(message = "O conteúdo não pode estar em branco!") String conteudoTextoDaPostagem) {
        this.conteudoTextoDaPostagem = conteudoTextoDaPostagem;
    }
}
