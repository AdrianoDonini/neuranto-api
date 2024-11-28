package br.com.neuranto.api.neuranto.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensagem")
    Long idMensagem;

    @Column(name = "mensagem_text")
    String mensagemText;

    @JoinColumn(name = "usuario_mensagem", nullable = false)
    @ManyToOne
    Usuario usuarioMensagem;

    @Column (name = "date_mensagem")
    LocalDate dataMensagem;

    public Long getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getMensagemText() {
        return mensagemText;
    }

    public void setMensagemText(String mensagemText) {
        this.mensagemText = mensagemText;
    }

    public Usuario getUsuarioMensagem() {
        return usuarioMensagem;
    }

    public void setUsuarioMensagem(Usuario usuarioMensagem) {
        this.usuarioMensagem = usuarioMensagem;
    }

    public LocalDate getDataMensagem() {
        return dataMensagem;
    }

    public void setDataMensagem(LocalDate dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensagem mensagem = (Mensagem) o;
        return Objects.equals(idMensagem, mensagem.idMensagem);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMensagem);
    }

    @PrePersist
    public void setaDate(){
        this.setDataMensagem(LocalDate.now());
    }
}
