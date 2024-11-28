package br.com.neuranto.api.neuranto.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {
    @NotNull(message = "O E-mail precisa ser informado!")
    @NotBlank
    private  String email;

    @NotNull(message = "A senha precisa ser informada!")
    private String senha;

    public @NotNull(message = "O E-mail precisa ser informado!") @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "O E-mail precisa ser informado!") @NotBlank String email) {
        this.email = email;
    }

    public @NotNull(message = "A senha precisa ser informada!") String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull(message = "A senha precisa ser informada!") String senha) {
        this.senha = senha;
    }
}
