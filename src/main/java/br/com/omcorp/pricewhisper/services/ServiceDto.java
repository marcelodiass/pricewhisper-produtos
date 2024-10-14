package br.com.omcorp.pricewhisper.services;

import java.util.List;
import java.util.Optional;

public interface ServiceDto<ID_TYPE, ENTITY>{
    List<ENTITY> getAll();
    Optional<ENTITY> getById(ID_TYPE id);
    void save(ENTITY entity);
    void update(ENTITY oldEntity, ENTITY newEntity);
    void delete(ID_TYPE id);
    void setEntity(ENTITY oldEntity, ENTITY newEntity);
}
