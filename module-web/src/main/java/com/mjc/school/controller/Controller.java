package com.mjc.school.controller;

import java.util.List;

public interface Controller<T, S, K> {

    List<T> readAll();

    T readById(K id);

    T create(S t);

    T update(S t);

    Boolean delete(K id);
}