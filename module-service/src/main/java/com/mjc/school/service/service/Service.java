package com.mjc.school.service.service;

import java.util.List;

public interface Service<T, S, K> {

    List<T> findAll();

    T findById(K id);

    T create(S t);

    T update(K id, S t);

    void delete(K id);
}
