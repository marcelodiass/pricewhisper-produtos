package br.com.omcorp.pricewhisper.repository;

import br.com.omcorp.pricewhisper.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
