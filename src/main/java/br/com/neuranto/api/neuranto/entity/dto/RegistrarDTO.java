package br.com.neuranto.api.neuranto.entity.dto;

import br.com.neuranto.api.neuranto.Enumerated.RegraUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegistrarDTO {

    @NotNull(message = "O nome do usuário precisar ser informado!")
    @NotBlank
    private String nome;

    @NotNull(message = "O campo E-mail precisar ser informado!")
    @NotBlank

    private String email;

    @NotNull(message = "O campo senha precisar ser informado!")
    @NotBlank
    private String senha;

    public RegraUsuario getRegra() {
        return regra;
    }

    public void setRegra(RegraUsuario regra) {
        this.regra = regra;
    }

    private RegraUsuario regra;



    public @NotNull(message = "O nome do usuário precisar ser informado!") @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "O nome do usuário precisar ser informado!") @NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O campo E-mail precisar ser informado!") @NotBlank  String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "O campo E-mail precisar ser informado!") @NotBlank @Email(message = "Informe um e-mail válido!") String email) {
        this.email = email;
    }

    public @NotNull(message = "O campo senha precisar ser informado!") @NotBlank String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull(message = "O campo senha precisar ser informado!") @NotBlank String senha) {
        this.senha = senha;
    }
}

