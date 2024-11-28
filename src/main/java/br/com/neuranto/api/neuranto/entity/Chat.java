package br.com.neuranto.api.neuranto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat")
    Long idChat;

    @JoinColumn(name = "usuario_chat_1", nullable = false)
    @ManyToOne
    Usuario usuarioChat1;

    @JoinColumn(name = "usuario_chat_2", nullable = false)
    @ManyToOne
    Usuario usuarioChat2;

    @Column(name = "data_abertura")
    LocalDate  dataAbertura;

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public Usuario getUsuarioChat1() {
        return usuarioChat1;
    }

    public void setUsuarioChat1(Usuario usuarioChat1) {
        this.usuarioChat1 = usuarioChat1;
    }

    public Usuario getUsuarioChat2() {
        return usuarioChat2;
    }

    public void setUsuarioChat2(Usuario usuarioChat2) {
        this.usuarioChat2 = usuarioChat2;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(idChat, chat.idChat);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idChat);
    }

    @PrePersist
    public void setDate(){
        this.setDataAbertura(LocalDate.now());
    }
}
