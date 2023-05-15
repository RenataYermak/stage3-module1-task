package com.mjc.school.service.impl;

import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NewsServiceTest {
    
    private final NewsServiceImpl service = new NewsServiceImpl();
    
    @Test
    void create() {
        NewsRequestDto newsResponseDto = new NewsRequestDto("Test news.txt", "This is a test news.txt", 1L);
        
        NewsResponseDto createdNews = service.create(newsResponseDto);
        
        assertNotEquals(createdNews.getId(), 0L);
        assertEquals(newsResponseDto.getTitle(), createdNews.getTitle());
        assertEquals(newsResponseDto.getContent(), createdNews.getContent());
        assertEquals(newsResponseDto.getAuthorId(), createdNews.getAuthorId());
    }

    @Test
    void getAll() {
        List<NewsResponseDto> freshNewsList = service.findAll();
        assertNotEquals(freshNewsList.size(), 0);

        NewsRequestDto newsResponseDto1 = new NewsRequestDto("Some title news1.txt", "Some description news1.txt", 1L);
        NewsRequestDto newsResponseDto2 = new NewsRequestDto("Some title news2.txt", "Some description news1.txt", 2L);
        NewsResponseDto created1 = service.create(newsResponseDto1);
        NewsResponseDto created2 = service.create(newsResponseDto2);
        List<NewsResponseDto> newsList = service.findAll();

        assertTrue(newsList.containsAll(List.of(created1, created2)));
    }

    @Test
    void getById() {
        NewsRequestDto newsResponseDto = new NewsRequestDto("Some title news.txt", "Some title news.txt", 1L);
        NewsResponseDto createdNews = service.create(newsResponseDto);

        NewsResponseDto retrievedNews = service.findById(createdNews.getId());

        assertEquals(createdNews, retrievedNews);
    }

    @Test
    void update() {
        NewsRequestDto newsResponseDto = new NewsRequestDto("Some title news.txt", "Some title news.txt", 1L);
        NewsResponseDto createdNews = service.create(newsResponseDto);
        NewsRequestDto updatedNewsRequestDto = new NewsRequestDto("Updated test news.txt", "Updated test news.txt", 2L);

        NewsResponseDto updatedNews = service.update(createdNews.getId(), updatedNewsRequestDto);

        assertEquals(updatedNewsRequestDto.getTitle(), updatedNews.getTitle());
        assertEquals(updatedNewsRequestDto.getContent(), updatedNews.getContent());
        assertEquals(updatedNewsRequestDto.getAuthorId(), updatedNews.getAuthorId());
    }

    @Test
    void delete() {
        NewsRequestDto newsResponseDto = new NewsRequestDto("Some title news.txt", "Some title news.txt", 1L);
        NewsResponseDto createdNews = service.create(newsResponseDto);

        service.delete(createdNews.getId());
        List<NewsResponseDto> newsList = service.findAll();

        assertFalse(newsList.contains(createdNews));
        assertThrows(NotFoundException.class, () -> service.findById(createdNews.getId()));
    }

    @Test
    public void testCreateNewsWithNonExistingAuthor() {
        NewsRequestDto newsResponseDto = new NewsRequestDto("Some title news.txt", "Some title news.txt", 9999999999L);

        assertThrows(NotFoundException.class, () -> service.create(newsResponseDto));
    }

    @Test
    public void testCreateNewsWithInvalidData() {
        NewsRequestDto newsResponseDto = new NewsRequestDto("INL", "INL", 1L);

        assertThrows(ValidationException.class, () -> service.create(newsResponseDto));
    }

    @Test
    public void testUpdateNonExistingNews() {
        NewsRequestDto newsResponseDto = new NewsRequestDto("Some title news.txt", "Some title news.txt", 1L);

        assertThrows(NotFoundException.class, () -> service.update(999L, newsResponseDto));
    }

    @Test
    public void testDeleteNonExistingNews() {
        assertThrows(NotFoundException.class, () -> service.delete(999L));
    }
}