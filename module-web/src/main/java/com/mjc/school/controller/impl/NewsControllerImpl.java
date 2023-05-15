package com.mjc.school.controller.impl;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.service.NewsService;

import java.util.List;

public class NewsControllerImpl implements Controller<NewsResponseDto, NewsRequestDto, Long> {
    private final NewsService newsService;

    public NewsControllerImpl(NewsService newsService) {
        this.newsService = newsService;
    }

    public List<NewsResponseDto> findAll() {
        return newsService.findAll();
    }

    public NewsResponseDto findById(Long newsId) {
        return newsService.findById(newsId);
    }

    public NewsResponseDto create(NewsRequestDto newsRequestDto) {
        return newsService.create(newsRequestDto);
    }

    public NewsResponseDto update(Long id, NewsRequestDto newsRequestDto) {
        return newsService.update(id,newsRequestDto);
    }

    public void delete(Long newsId) {
       newsService.delete(newsId);
    }
}