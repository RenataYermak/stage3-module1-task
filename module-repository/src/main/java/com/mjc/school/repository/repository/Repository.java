package com.mjc.school.repository.repository;

import java.util.List;

public interface Repository<T, K> {

    List<T> findAll();

    T findById(K id);

    T update(K id, T t);

    T create(T t);

    void delete(K id);

    boolean isExist(K id);
}
