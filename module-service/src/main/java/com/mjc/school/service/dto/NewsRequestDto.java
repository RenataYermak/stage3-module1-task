package com.mjc.school.service.dto;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class NewsRequestDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long authorId;
}
