package com.sydoruk1ua.mdmg.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    Optional<Integer> create(T entity);

    Optional<T> findById(Integer id);

    List<T> findAll();

    void update(T entity);

    void deleteById(Integer id);
}
