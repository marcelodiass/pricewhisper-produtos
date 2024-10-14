package br.com.omcorp.pricewhisper.services;

import br.com.omcorp.pricewhisper.model.Produto;
import br.com.omcorp.pricewhisper.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements ServiceDto<Long, Produto> {
    
    @Autowired
    private ProdutoRepository repo;
    
    @Override
    public List<Produto> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Produto> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void save(Produto produto) {
        repo.save(produto);
    }

    @Override
    public void update(Produto oldEntity, Produto newEntity) {
        this.setEntity(oldEntity, newEntity);
        repo.save(oldEntity);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void setEntity(Produto oldEntity, Produto newEntity) {
        oldEntity.setNome(newEntity.getNome());
        oldEntity.setDescricao(newEntity.getDescricao());
        oldEntity.setPrecoCusto(newEntity.getPrecoCusto());
        oldEntity.setPrecoMinimo(newEntity.getPrecoMinimo());
        oldEntity.setPrecoVenda(newEntity.getPrecoVenda());
        oldEntity.setEstoque(newEntity.getEstoque());
        oldEntity.setModelo(newEntity.getModelo());
        oldEntity.setCategoria(newEntity.getCategoria());
    }
}
