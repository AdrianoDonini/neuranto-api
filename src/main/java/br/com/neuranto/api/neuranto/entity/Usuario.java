package br.com.neuranto.api.neuranto.entity;

import br.com.neuranto.api.neuranto.Enumerated.RegraUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Define a estratégia de herança como JOINED
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nome_usuario", nullable = false, length = 200)
    @NotNull(message = "O campo nome não pode ser vazio!")
    @Length(min = 3, max = 200, message = "O nome deve ter entre 3 e 200 caracteres!")
    private String nomeUsuario;

    @Column(name = "email_usuario", nullable = false, length = 80)
    @Email
    @NotNull(message = "O campo e-mail não pode ser vazio!")
    private String emailUsuario;

    @Column(name = "password", nullable = false)
    @NotNull(message = "O campo senha não pode ser vazio!")
    private String password;

    @Column(name = "regra_usuario")
    private RegraUsuario regraUsuario;

    @Column(name = "sobre_usuario", length = 1000)
    private String sobreUsuario;

    @Column(name = "data_de_craicao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeCriacao;

    @Column(name = "data_de_alteracao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeAlteracao;

    @PrePersist
    private void prePersist(){
        this.setDataDeCriacao(LocalDate.now());
    }

    @PreUpdate
    private void preUpadate(){
        this.setDataDeAlteracao(LocalDate.now());
    }

    public Usuario() {
    }

    public Usuario(String nomeUsuario, String emailUsuario, String password, RegraUsuario regraUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.password = password;
        this.regraUsuario = regraUsuario;
    }

    public Usuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public RegraUsuario getRegraUsuario() {
        return regraUsuario;
    }

    public void setRegraUsuario(RegraUsuario regraUsuario) {
        this.regraUsuario = regraUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
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

    public @NotNull(message = "O campo senha não pode ser vazio!") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "O campo senha não pode ser vazio!") String password) {
        this.password = password;
    }

    public String getSobreUsuario() {
        return sobreUsuario;
    }

    public void setSobreUsuario(String sobreUsuario) {
        this.sobreUsuario = sobreUsuario;
    }

    public LocalDate getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDate dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public LocalDate getDataDeAlteracao() {
        return dataDeAlteracao;
    }

    public void setDataDeAlteracao(LocalDate dataDeAlteracao) {
        this.dataDeAlteracao = dataDeAlteracao;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("This emailUsuario:"+emailUsuario);
        System.out.println(o);
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        System.out.println("usuario.emailUsuario:"+usuario.emailUsuario);
        return Objects.equals(emailUsuario, usuario.emailUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(emailUsuario);
    }
}

