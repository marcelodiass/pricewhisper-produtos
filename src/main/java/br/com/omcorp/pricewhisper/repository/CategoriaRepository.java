package br.com.omcorp.pricewhisper.repository;

import br.com.omcorp.pricewhisper.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
