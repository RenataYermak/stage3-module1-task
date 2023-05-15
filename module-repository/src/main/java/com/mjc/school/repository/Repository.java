package com.mjc.school.repository;

import java.util.List;

public interface Repository<T, K> {

    List<T> readAll();

    T readById(K id);

    T update(K id, T t);

    T create(T t);

    boolean delete(K id);

    boolean isExist(K id);
}
