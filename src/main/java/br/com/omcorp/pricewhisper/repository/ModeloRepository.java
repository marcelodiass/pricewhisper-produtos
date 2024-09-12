package br.com.omcorp.pricewhisper.repository;

import br.com.omcorp.pricewhisper.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}
