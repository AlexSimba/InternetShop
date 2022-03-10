package com.alexsimba.attestation.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, InvalidDataAccessApiUsageException.class})
    protected ResponseEntity<ApiError> handleEntityNotFoundEx() {
        ApiError apiError = new ApiError("Запрашиваемый параметр отсутствует или имеет некорректный формат");
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError("Некорректный запрос JSON");
        return new ResponseEntity(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError("Аргумент метода недопустим");
        return new ResponseEntity<>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError("Аргумент метода недопустим");
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage(String.format("Параметр '%s' значения '%s' не удалось преобразовать в тип'%s'",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));

        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError("Обработчик не найден"), status);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions() {
        ApiError apiError = new ApiError("Внутреннее исключение");
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}