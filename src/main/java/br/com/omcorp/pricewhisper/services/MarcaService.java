package br.com.omcorp.pricewhisper.services;

import br.com.omcorp.pricewhisper.model.Marca;
import br.com.omcorp.pricewhisper.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService implements ServiceDto<Long, Marca> {
    
    @Autowired
    MarcaRepository repo;
    
    @Override
    public List<Marca> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Marca> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void save(Marca marca) {
        repo.save(marca);
    }

    @Override
    public void update(Marca oldMarca, Marca newMarca) {
        this.setEntity(oldMarca, newMarca);
        repo.save(oldMarca);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void setEntity(Marca oldMarca, Marca newMarca) {
        oldMarca.setNome(newMarca.getNome());
        oldMarca.setDescricao(newMarca.getDescricao());
    }
}
