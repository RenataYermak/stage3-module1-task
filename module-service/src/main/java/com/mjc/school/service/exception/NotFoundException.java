package com.mjc.school.service.exception;

public class NotFoundException extends BaseException {

    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(ErrorCode.NOT_FOUND, message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(ErrorCode.NOT_FOUND, message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(ErrorCode.NOT_FOUND, cause);
    }
}
