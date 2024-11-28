package br.com.neuranto.api.neuranto.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity

public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postagem")
    private Long idPostagem;

    @JoinColumn(nullable = false, name = "id_usuario")
    @ManyToOne
    private Usuario usuario;

    @Column(name = "titulo_da_postagem", nullable = false)
    private String tituloDaPostagem;

    @Column(name = "descricao_da_postagem")
    private String descricaoDaPostagem;

    @Column(name = "conteudo_texto_da_postagem", nullable = false)
    private String conteudoTextoDaPostagem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Postagem postagem = (Postagem) o;
        return Objects.equals(idPostagem, postagem.idPostagem) && Objects.equals(usuario, postagem.usuario) && Objects.equals(tituloDaPostagem, postagem.tituloDaPostagem) && Objects.equals(descricaoDaPostagem, postagem.descricaoDaPostagem) && Objects.equals(conteudoTextoDaPostagem, postagem.conteudoTextoDaPostagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPostagem, usuario, tituloDaPostagem, descricaoDaPostagem, conteudoTextoDaPostagem);
    }

    public Postagem() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTituloDaPostagem() {
        return tituloDaPostagem;
    }

    public void setTituloDaPostagem(String tituloDaPostagem) {
        this.tituloDaPostagem = tituloDaPostagem;
    }

    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
    }

    public String getDescricaoDaPostagem() {
        return descricaoDaPostagem;
    }

    public void setDescricaoDaPostagem(String descricaoDaPostagem) {
        this.descricaoDaPostagem = descricaoDaPostagem;
    }

    public String getConteudoTextoDaPostagem() {
        return conteudoTextoDaPostagem;
    }

    public void setConteudoTextoDaPostagem(String conteudoTextoDaPostagem) {
        this.conteudoTextoDaPostagem = conteudoTextoDaPostagem;
    }
}
