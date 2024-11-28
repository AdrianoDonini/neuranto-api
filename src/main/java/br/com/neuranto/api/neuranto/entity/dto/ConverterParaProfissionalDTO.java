package br.com.neuranto.api.neuranto.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class ConverterParaProfissionalDTO {
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

    public @NotNull(message = "O CRP precisa ser informado!") @NotBlank String getCrp() {
        return crp;
    }

    public void setCrp(@NotNull(message = "O CRP precisa ser informado!") @NotBlank String crp) {
        this.crp = crp;
    }

    public boolean isValidarPerfil() {
        return validarPerfil;
    }

    public void setValidarPerfil(boolean validarPerfil) {
        this.validarPerfil = validarPerfil;
    }


}
