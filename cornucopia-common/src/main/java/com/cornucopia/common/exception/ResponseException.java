package com.cornucopia.common.exception;

import com.cornucopia.common.enums.ExceptionEnum;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-08-14
 */
public class ResponseException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

    public ResponseException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
