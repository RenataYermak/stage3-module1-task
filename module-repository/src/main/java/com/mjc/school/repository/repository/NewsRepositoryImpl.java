package com.mjc.school.repository.repository;

import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.News;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

public class NewsRepositoryImpl implements Repository<News, Long> {

    private final List<News> newsList;

    public NewsRepositoryImpl() throws IOException {
        DataSource dataSource = DataSource.getInstance();
        newsList = dataSource.getNewsStorage();
    }

    @Override
    public News create(News news) {
        newsList.add(news);
        return news;
    }

    @Override
    public List<News> findAll() {
        return newsList;
    }

    @Override
    public News findById(Long id) {
        return newsList.stream()
                .filter(news -> news.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public News update(Long id, News entity) {
        News news = findById(id);
        news.setTitle(entity.getTitle());
        news.setContent(entity.getContent());
        news.setAuthorId(entity.getAuthorId());
        news.setLastUpdateDate(LocalDateTime.now());
        return news;
    }

    @Override
    public void delete(Long id) {
        if (!newsList.removeIf(news -> news.getId() == id)) {
            throw new NoSuchElementException("No found value");
        }
    }

    @Override
    public boolean isExist(Long id) {
        for (News news : newsList) {
            if (news.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
