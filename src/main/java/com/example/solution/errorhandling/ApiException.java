package com.example.solution.errorhandling;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException{

    private final HttpStatus status;

    private final String exceptionDetail;


    public ApiException(HttpStatus status, String exceptionDetail) {
        super(exceptionDetail);

        this.status = status;
        this.exceptionDetail = exceptionDetail;
    }
}
