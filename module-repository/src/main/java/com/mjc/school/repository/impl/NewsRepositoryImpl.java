package com.mjc.school.repository.impl;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.NewsModel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class NewsRepositoryImpl implements Repository<NewsModel, Long> {

    private final List<NewsModel> newsList;
    private final DataSource dataSource;

    public NewsRepositoryImpl() throws IOException {
        dataSource = DataSource.getInstance();
        newsList = dataSource.getNewsStorage();
    }

    @Override
    public NewsModel create(NewsModel news) {
        newsList.add(news);
        return news;
    }

    @Override
    public List<NewsModel> readAll() {
        return newsList;
    }

    @Override
    public NewsModel readById(Long id) {
        return newsList.stream()
                .filter(news -> news.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public NewsModel update(NewsModel entity) {

        NewsModel news = readById(entity.getId());
        news.setTitle(entity.getTitle());
        news.setContent(entity.getContent());
        news.setAuthorId(entity.getAuthorId());
        news.setLastUpdateDate(LocalDateTime.now());
        return news;
    }

    @Override
    public Boolean deleteById(Long id) {
        return newsList.removeIf(news -> news.getId() == id);
    }

    @Override
    public Boolean isExist(Long id) {
        for (NewsModel news : newsList) {
            if (news.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
