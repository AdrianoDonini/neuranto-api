package br.com.neuranto.api.neuranto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity

public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;

    @JoinColumn(nullable = false, name = "id_usuario")
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(nullable = false, name = "id_postagem")
    @ManyToOne
    private Postagem postagem;

    @Column(name = "comentario", nullable = false)
    @NotBlank(message = "O campo comentario não pode ser vazio!")
    @NotNull(message = "O campo comentario precisa ser informado!")
    private String comentario;

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public Usuario getUsuairo() {
        return usuario;
    }

    public void setUsuario(Usuario idUsuairo) {
        this.usuario = idUsuairo;
    }

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }

    public @NotBlank(message = "O campo comentario não pode ser vazio!") @NotNull(message = "O campo comentario precisa ser informado!") String getComentario() {
        return comentario;
    }

    public void setComentario(@NotBlank(message = "O campo comentario não pode ser vazio!") @NotNull(message = "O campo comentario precisa ser informado!") String comentario) {
        this.comentario = comentario;
    }
}
