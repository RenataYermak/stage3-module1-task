package com.mjc.school.repository;

import java.util.List;

public interface Repository<T, K> {

    List<T> readAll();

    T readById(K id);

    T update(T t);

    T create(T t);

    Boolean deleteById(K id);

    Boolean isExist(K id);
}
