package br.com.neuranto.api.neuranto.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ComentarioDTO {

    private Long idComentario;

    private Long usuario;

    private Long postagem;

    @NotBlank(message = "O campo comentario não pode ser vazio!")
    @NotNull(message = "O campo comentario precisa ser informado!")
    private String comentario;

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Long getPostagem() {
        return postagem;
    }

    public void setPostagem(Long postagem) {
        this.postagem = postagem;
    }

    public @NotBlank(message = "O campo comentario não pode ser vazio!") @NotNull(message = "O campo comentario precisa ser informado!") String getComentario() {
        return comentario;
    }

    public void setComentario(@NotBlank(message = "O campo comentario não pode ser vazio!") @NotNull(message = "O campo comentario precisa ser informado!") String comentario) {
        this.comentario = comentario;
    }
}
