package org.ecommerce.services.impl;

import lombok.Getter;
import org.ecommerce.util.CrudOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public T findById(Long id) {
        if (existsById(id))
            return db.get(id);
        else
            throw new IllegalArgumentException("Entity not found for update.");
    }

    @Override
    public void deleteById(Long id) {
        if (existsById(id))
            db.remove(id);
        else
            throw new IllegalArgumentException("Entity not found for delete.");

    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(db.values());
    }

    private boolean existsById(Long id) {
        return db.containsKey(id);
    }

    private Long generateId() {
        return id++;
    }

}
