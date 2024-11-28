package br.com.neuranto.api.neuranto.repository;

import br.com.neuranto.api.neuranto.entity.Postagem;
import br.com.neuranto.api.neuranto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Postagem, Long> {

    List<Postagem> findByUsuario(Usuario usuario);
}
