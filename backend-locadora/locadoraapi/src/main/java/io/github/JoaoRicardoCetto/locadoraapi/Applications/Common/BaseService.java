package io.github.JoaoRicardoCetto.locadoraapi.Applications.Common;

import io.github.JoaoRicardoCetto.locadoraapi.model.Common.BaseEntity;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseService<T extends BaseEntity> {

    protected abstract IBaseRepository<T> getRepository();

    public T salvar(T entity) {

        return getRepository().save(entity);
    }

    public Optional<T> obterPorId(UUID id) {

        return getRepository().findById(id);
    }

    public List<T> obterTodos() {
        return getRepository().findAll();
    }

    public void deletar(T entity) {

        getRepository().delete(entity);
    }

    public T atualizar(T entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("A entidade deve possuir um ID para ser atualizada.");
        }
        return salvar(entity);
    }
}
