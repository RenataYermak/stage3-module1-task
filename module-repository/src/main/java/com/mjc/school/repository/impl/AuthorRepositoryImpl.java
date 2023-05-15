package com.mjc.school.repository.impl;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AuthorRepositoryImpl implements Repository<AuthorModel, Long> {

    private final List<AuthorModel> authors;
    private final DataSource dataSource;

    public AuthorRepositoryImpl() throws IOException {
        dataSource = DataSource.getInstance();
        authors = dataSource.getAuthorStorage();
    }

    @Override
    public AuthorModel create(AuthorModel author) {
        authors.add(author);
        return author;
    }

    @Override
    public List<AuthorModel> readAll() {
        return (List<AuthorModel>) Collections.unmodifiableCollection(authors);
    }

    @Override
    public AuthorModel readById(Long id) {
        return authors.stream()
                .filter(author -> author.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public AuthorModel update( AuthorModel entity) {
        AuthorModel author = readById(entity.getId());
        author.setName(entity.getName());
        return author;
    }

    @Override
    public Boolean deleteById(Long id) {
        return authors.removeIf(author -> author.getId() == id);
    }

    @Override
    public Boolean isExist(Long id) {
        for (AuthorModel author : authors) {
            if (author.getId() == id)
                return true;
        }
        return false;
    }
}
