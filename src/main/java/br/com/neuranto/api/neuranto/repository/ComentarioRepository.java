package br.com.neuranto.api.neuranto.repository;

import br.com.neuranto.api.neuranto.entity.Comentario;
import br.com.neuranto.api.neuranto.entity.Postagem;
import br.com.neuranto.api.neuranto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByUsuario(Usuario usuario);
    List<Comentario> findByPostagem(Postagem postagem);
}
