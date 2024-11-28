package br.com.neuranto.api.neuranto.entity.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;

public class UsuarioProfissionalDTO {

    private Long idUsuario;

    @NotNull(message = "O campo nome não pode ser vazio!")
    @Length(min = 3, max = 200, message = "O nome deve ter entre 3 e 200 caracteres!")
    private String nomeUsuario;


    @Email
    @NotNull(message = "O campo e-mail não pode ser vazio!")
    private String emailUsuario;

    @NotNull(message = "O campo senha não pode ser vazio!")
    private String senhaUsuario;

    private String regraUsuario;

    @Length(max = 2000, message = "A descrição só pode ter apenas 2000 caractéres!")
    private String sobreUsuario;

    @NotNull(message = "O CPF precisa ser informado!")
    @NotBlank
    @CPF(message = "O CPF precisa ser válido!")
    private String cpf;

    @NotNull(message = "O CNPJ precisa ser informado!")
    @NotBlank
    @CNPJ(message = "O CNPJ precisa ser válido!")
    private String cnpj;
    // 00/00000000000

    @NotNull(message = "O CRP precisa ser informado!")
    @NotBlank
    private String crp;

    private boolean validarPerfil = false;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getRegraUsuario() {
        return regraUsuario;
    }

    public void setRegraUsuario(String regraUsuario) {
        this.regraUsuario = regraUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public @NotNull(message = "O campo nome não pode ser vazio!") @Length(min = 3, max = 200, message = "O nome deve ter entre 3 e 200 caracteres!") String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(@NotNull(message = "O campo nome não pode ser vazio!") @Length(min = 3, max = 200, message = "O nome deve ter entre 3 e 200 caracteres!") String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public @Email @NotNull(message = "O campo e-mail não pode ser vazio!") String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(@Email @NotNull(message = "O campo e-mail não pode ser vazio!") String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public @NotNull(message = "O campo senha não pode ser vazio!") String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(@NotNull(message = "O campo senha não pode ser vazio!") String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public @Length(max = 2000, message = "A descrição só pode ter apenas 2000 caractéres!") String getSobreUsuario() {
        return sobreUsuario;
    }

    public void setSobreUsuario(@Length(max = 2000, message = "A descrição só pode ter apenas 2000 caractéres!") String sobreUsuario) {
        this.sobreUsuario = sobreUsuario;
    }

    public @NotNull(message = "O CPF precisa ser informado!") @NotBlank @CPF(message = "O CPF precisa ser válido!") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull(message = "O CPF precisa ser informado!") @NotBlank @CPF(message = "O CPF precisa ser válido!") String cpf) {
        this.cpf = cpf;
    }

    public @NotNull(message = "O CNPJ precisa ser informado!") @NotBlank @CNPJ(message = "O CNPJ precisa ser válido!") String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@NotNull(message = "O CNPJ precisa ser informado!") @NotBlank @CNPJ(message = "O CNPJ precisa ser válido!") String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean isValidarPerfil() {
        return validarPerfil;
    }

    public void setValidarPerfil(boolean validarPerfil) {
        this.validarPerfil = validarPerfil;
    }

    public @NotNull(message = "O CRP precisa ser informado!") @NotBlank String getCrp() {
        return crp;
    }

    public void setCrp(@NotNull(message = "O CRP precisa ser informado!") @NotBlank String crp) {
        this.crp = crp;
    }

}
