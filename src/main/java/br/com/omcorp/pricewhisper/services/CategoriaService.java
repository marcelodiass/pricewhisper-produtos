package br.com.omcorp.pricewhisper.services;

import br.com.omcorp.pricewhisper.model.Categoria;
import br.com.omcorp.pricewhisper.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ServiceDto<Long, Categoria> {
    
    @Autowired
    CategoriaRepository repo;
    
    @Override
    public List<Categoria> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Categoria> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void save(Categoria categoria) {
        repo.save(categoria);
    }

    @Override
    public void update(Categoria oldCategoria, Categoria newCategoria) {
        this.setEntity(oldCategoria, newCategoria);
        repo.save(oldCategoria);
    }


    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    @Override
    public void setEntity(Categoria oldCategoria, Categoria newCategoria) {
        oldCategoria.setNome(newCategoria.getNome());
        oldCategoria.setDescricao(newCategoria.getDescricao());
    }
}
