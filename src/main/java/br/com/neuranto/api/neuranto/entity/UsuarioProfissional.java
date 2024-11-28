package br.com.neuranto.api.neuranto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;


@Entity

public class UsuarioProfissional extends Usuario{
    @Column(name = "cpf", nullable = false, length = 14)
    @CPF
    private String cpf;

    @Column(name = "cnpj", nullable = false, length = 18)
    @CNPJ
    private String cnpj;
    // 00/00000000000
    @Column(name = "crp", nullable = false, length = 14)
    private String crp;

    @Column(name = "validar_perfil", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean validarPerfil;

    public UsuarioProfissional() {

    }

    public @CPF String getCpf() {
        return cpf;
    }

    public void setCpf(@CPF String cpf) {
        this.cpf = cpf;
    }

    public @CNPJ String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@CNPJ String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }

    public boolean isValidarPerfil() {
        return validarPerfil;
    }

    public void setValidarPerfil(boolean validarPerfil) {
        this.validarPerfil = validarPerfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioProfissional that = (UsuarioProfissional) o;
        return validarPerfil == that.validarPerfil && Objects.equals(cpf, that.cpf) && Objects.equals(cnpj, that.cnpj) && Objects.equals(crp, that.crp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, cnpj, crp, validarPerfil);
    }

}
