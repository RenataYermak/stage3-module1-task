package com.mjc.school.service.validation;

import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.exception.ValidationException;

public class NewsValidatorImpl implements Validator<NewsRequestDto> {

    @Override
    public void validate(NewsRequestDto newsRequestDto) throws ValidationException {
        String title = newsRequestDto.getTitle();
        String content = newsRequestDto.getContent();
        if (title != null) {
            if (title.length() < 5 || title.length() > 30) {
                throw new ValidationException("Title field should have length of value from 5 to 30.");
            }
        }
        if (content != null) {
            if (content.length() < 5 || content.length() > 255) {
                throw new ValidationException("Content field should have length of value from 5 to 255.");
            }
        }
    }
}
