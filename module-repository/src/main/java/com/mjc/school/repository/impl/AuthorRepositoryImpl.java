package com.mjc.school.repository.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.Author;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class AuthorRepositoryImpl implements Repository<Author, Long> {

    private final List<Author> authors;

    public AuthorRepositoryImpl() throws IOException {
        DataSource dataSource = DataSource.getInstance();
        authors = dataSource.getAuthorStorage();
    }

    @Override
    public Author create(Author author) {
        authors.add(author);
        return author;
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) Collections.unmodifiableCollection(authors);
    }

    @Override
    public Author findById(Long id) {
        return authors.stream()
                .filter(author -> author.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Author update(Long id, Author entity) {
        Author author = findById(id);
        author.setName(entity.getName());
        return author;
    }

    @Override
    public void delete(Long id) {
        if (!authors.removeIf(author -> author.getId() == id)) {
            throw new NoSuchElementException("Not found value ");
        }
    }

    @Override
    public boolean isExist(Long id) {
        for (Author author : authors) {
            if (author.getId() == id)
                return true;
        }
        return false;
    }
}
