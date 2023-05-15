package com.mjc.school.service.impl;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.impl.AuthorRepositoryImpl;
import com.mjc.school.repository.impl.NewsRepositoryImpl;
import com.mjc.school.repository.Repository;
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

    private final Repository<News, Long> newsRepository;
    private final Repository<Author, Long> authorRepository;
    private final Validator<NewsRequestDto> newsValidator;
    private final NewsMapper mapper = NewsMapper.INSTANCE;

    {
        try {
            newsRepository = new NewsRepositoryImpl();
            authorRepository = new AuthorRepositoryImpl();
            newsValidator = new NewsValidatorImpl();
        } catch (IOException e) {
            throw new InternalServerErrorException("Unable to initialize internal database. " +
                    "Source files are missing or inaccessible.");
        }
    }

    @Override
    public NewsResponseDto create(NewsRequestDto newsRequestDto) {
        validate(newsRequestDto);

        News news = mapper.mapRequestToNews(newsRequestDto);
        News savedNews = newsRepository.create(news);
        return mapper.mapNewsToResponse(savedNews);
    }

    @Override
    public List<NewsResponseDto> findAll() {
        return mapper.mapNewsToResponseDtoList(newsRepository.findAll());
    }

    @Override
    public NewsResponseDto findById(Long id) {
        News news = getNewsById(id);
        return mapper.mapNewsToResponse(news);
    }

    @Override
    public NewsResponseDto update(Long id, NewsRequestDto newsRequestDto) {
        validate(newsRequestDto);

        News news = getNewsById(id);
        news.setTitle(newsRequestDto.getTitle());
        news.setContent(newsRequestDto.getContent());
        news.setAuthorId(newsRequestDto.getAuthorId());

        News savedNews = newsRepository.update(id, news);
        return mapper.mapNewsToResponse(savedNews);
    }

    @Override
    public void delete(Long id) {
        try {
            newsRepository.delete(id);
        } catch (Exception e) {
            throw new NotFoundException(String.format("News with ID %d not found.", id));
        }
    }

    private void validate(NewsRequestDto newsRequestDto) throws ValidationException {
        if (!authorRepository.isExist(newsRequestDto.getAuthorId()))
            throw new NotFoundException(String.format("Author with ID %d not found.", newsRequestDto.getAuthorId()));
         newsValidator.validate(newsRequestDto);
    }

    private News getNewsById(Long id) {
        News news;
        try {
            news = newsRepository.findById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(String.format("News with ID %d not found.", id));
        }
        return news;
    }
}
