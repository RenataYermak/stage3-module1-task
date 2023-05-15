package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.impl.NewsRepositoryImpl;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.exception.InternalServerErrorException;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.service.mapping.NewsMapper;
import com.mjc.school.service.validation.NewsValidatorImpl;
import com.mjc.school.service.validation.Validator;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class NewsServiceImpl implements Service<NewsResponseDto, NewsRequestDto, Long> {

    private final Repository<NewsModel, Long> newsRepository;
    private final Validator<NewsRequestDto> newsValidator;
    private final NewsMapper mapper = NewsMapper.INSTANCE;

    {
        try {
            newsRepository = new NewsRepositoryImpl();
            newsValidator = new NewsValidatorImpl();
        } catch (IOException e) {
            throw new InternalServerErrorException("Unable to initialize internal database. " +
                    "Source files are missing or inaccessible.");
        }
    }

    @Override
    public NewsResponseDto create(NewsRequestDto newsRequestDto) {
        validate(newsRequestDto);

        NewsModel news = mapper.mapRequestToNews(newsRequestDto);
        NewsModel savedNews = newsRepository.create(news);
        return mapper.mapNewsToResponse(savedNews);
    }

    @Override
    public List<NewsResponseDto> readAll() {
        return mapper.mapNewsToResponseDtoList(newsRepository.readAll());
    }

    @Override
    public NewsResponseDto readById(Long id) {
        NewsModel news = getNewsById(id);
        return mapper.mapNewsToResponse(news);
    }

    @Override
    public NewsResponseDto update(NewsRequestDto newsRequestDto) {
        validate(newsRequestDto);

        NewsModel news = getNewsById(newsRequestDto.getId());
        news.setTitle(newsRequestDto.getTitle());
        news.setContent(newsRequestDto.getContent());
        news.setAuthorId(newsRequestDto.getAuthorId());

        NewsModel savedNews = newsRepository.update(news);
        return mapper.mapNewsToResponse(savedNews);
    }

    @Override
    public Boolean delete(Long id) {
        try {
           return newsRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException(String.format("News with ID %d not found.", id));
        }
    }

    private void validate(NewsRequestDto newsRequestDto) throws ValidationException {
        newsValidator.validate(newsRequestDto);
    }

    private NewsModel getNewsById(Long id) {
        NewsModel news;
        try {
            news = newsRepository.readById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(String.format("News with ID %d not found.", id));
        }
        return news;
    }
}
