package br.com.omcorp.pricewhisper.services;

import br.com.omcorp.pricewhisper.model.Modelo;
import br.com.omcorp.pricewhisper.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService implements ServiceDto<Long, Modelo> {
    
    @Autowired
    private ModeloRepository repo;
    
    @Override
    public List<Modelo> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Modelo> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void save(Modelo modelo) {
        repo.save(modelo);
    }

    @Override
    public void update(Modelo oldEntity, Modelo newEntity) {
        this.setEntity(oldEntity, newEntity);
        repo.save(oldEntity);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void setEntity(Modelo oldEntity, Modelo newEntity) {
        oldEntity.setNome(newEntity.getNome());
        oldEntity.setDescricao(newEntity.getDescricao());
        oldEntity.setMarca(newEntity.getMarca());
    }
}
