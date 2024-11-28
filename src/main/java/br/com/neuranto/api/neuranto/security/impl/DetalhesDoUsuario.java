package br.com.neuranto.api.neuranto.security.impl;

import br.com.neuranto.api.neuranto.Enumerated.RegraUsuario;
import br.com.neuranto.api.neuranto.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@EqualsAndHashCode(of = "idUsuario")
public class DetalhesDoUsuario implements UserDetails {
    private Long idUsuario;

    private String nome;

    private String emailUsuario;

    private String senha;

    private  RegraUsuario regraUsuario;

    public DetalhesDoUsuario(Long idUsuario, String nome, String emailUsuario, String senha, RegraUsuario regraUsuario) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.emailUsuario = emailUsuario;
        this.senha = senha;
        this.regraUsuario = regraUsuario;
    }

    public DetalhesDoUsuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.regraUsuario == RegraUsuario.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return emailUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public RegraUsuario getRegraUsuario() {
        return regraUsuario;
    }
}
