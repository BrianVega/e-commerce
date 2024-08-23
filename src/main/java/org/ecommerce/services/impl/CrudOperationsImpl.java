package org.ecommerce.services.impl;

import lombok.Getter;
import org.ecommerce.util.CrudOperations;
import org.ecommerce.util.Error;

import java.util.*;

@Getter
public abstract class CrudOperationsImpl <T> implements CrudOperations<T, Long> {

    static Long id = 0L;
    Map<Long, T> db = new HashMap<>();

    @Override
    public T save(T entity) {
        Long id = generateId();
        db.put(id, entity);
        return db.get(id);
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public void update(Long id, T entity) {
        db.put(id, entity);
    }

    @Override
    public void deleteById(Long id) {
        findById(id)
                .ifPresentOrElse((user) -> db.remove(id),
                        () -> {throw new NoSuchElementException(Error.ENTITY_NOT_FOUND.getDescription());
                });
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(db.values());
    }

    private Long generateId() {
        return id++;
    }

}
