package com.example.solution.errorhandling;

import com.example.solution.api.model.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException apiException, WebRequest webrequest) {
        ApiError apiError = new ApiError();
        apiError.setError(apiException.getClass().getSimpleName());
        apiError.setErrorDescription(apiException.getExceptionDetail());

        return new ResponseEntity<>(apiError, apiException.getStatus());
    }
}
