package com.mjc.school.service.validation;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.exception.ValidationException;

public class AuthorValidatorImpl implements Validator<AuthorDto> {

    public void validate(AuthorDto authorDto) {
        int titleSize = authorDto.getName().length();
        if (titleSize<3 || titleSize>15)
            throw new ValidationException("Name should be from 5 to 30.");
    }
}
