package br.com.neuranto.api.neuranto.repository;

import br.com.neuranto.api.neuranto.entity.Usuario;
import br.com.neuranto.api.neuranto.security.impl.DetalhesDoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNomeUsuarioIgnoreCaseContaining(String nomeUsuario);

   Optional<Usuario> findByEmailUsuario (String emailUsuario);

    @Query(value = "SELECT c.* FROM usuario c WHERE c.data_de_craicao >= ?;", nativeQuery = true)
    List<Usuario> findByDataDeCriacao(@Param("dataCriacaoUsuario") LocalDate dataCriacaoUsuario);
}
