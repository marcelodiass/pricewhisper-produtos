package br.com.omcorp.pricewhisper.repository;

import br.com.omcorp.pricewhisper.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
