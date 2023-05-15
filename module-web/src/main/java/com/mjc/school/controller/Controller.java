package com.mjc.school.controller;

import java.util.List;

public interface Controller<T, S, K> {

    List<T> findAll();

    T findById(K id);

    T create(S t);

    T update(S t);

    void delete(K id);
}