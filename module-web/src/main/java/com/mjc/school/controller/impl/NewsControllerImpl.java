package com.mjc.school.controller.impl;

import com.mjc.school.controller.Controller;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.impl.NewsServiceImpl;

import java.util.List;

public class NewsControllerImpl implements Controller<NewsResponseDto, NewsRequestDto, Long> {

    private final Service<NewsResponseDto, NewsRequestDto, Long> newsService;

    public NewsControllerImpl(Service<NewsResponseDto, NewsRequestDto, Long> newsService) {
        this.newsService = newsService;

    }

    @Override
    public List<NewsResponseDto> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsResponseDto readById(Long newsId) {
        return newsService.readById(newsId);
    }

    @Override
    public NewsResponseDto create(NewsRequestDto newsRequestDto) {
        return newsService.create(newsRequestDto);
    }

    @Override
    public NewsResponseDto update(NewsRequestDto newsRequestDto) {
        return newsService.update(newsRequestDto);
    }

    @Override
    public Boolean delete(Long newsId) {
        return newsService.delete(newsId);
    }
}