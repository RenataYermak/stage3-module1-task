package com.mjc.school.service;

import java.util.List;

public interface Service<T, S, K> {

    List<T> readAll();

    T readById(K id);

    T create(S t);

    T update(S t);

    Boolean delete(K id);
}
