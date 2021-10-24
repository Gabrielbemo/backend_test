package com.socios.clube.esportes.controllers;

import com.socios.clube.esportes.controllers.dtos.out.ErrorFieldDTO;
import com.socios.clube.esportes.controllers.dtos.out.ErrorDTO;
import com.socios.clube.esportes.models.enums.ErrorsCode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handlerMethodArgumentNotValidException(final MethodArgumentNotValidException ex){
        List<ErrorFieldDTO> errorFieldDTO = ex
                .getAllErrors()
                .stream()
                .map(error -> (FieldError)error)
                .map(ErrorFieldDTO::fromFieldError)
                .collect(Collectors.toList());

        ErrorDTO errorDTO = ErrorDTO.from(BAD_REQUEST, ErrorsCode.ERROR_INVALID_ARGUMENTS,"Invalid Arguments", errorFieldDTO);

        return errorDTO;
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    @ResponseStatus(NOT_FOUND)
    public ErrorDTO handlerEmptyResultDataAccessException(final EmptyResultDataAccessException ex){

        ErrorDTO errorDTO = ErrorDTO.from(NOT_FOUND, ErrorsCode.EMPTY_RESULT_DATA_ACCESS, ex.getLocalizedMessage(), null);

        return errorDTO;
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public ErrorDTO handlerEntityNotFoundException(final EntityNotFoundException ex){

        ErrorDTO errorDTO = ErrorDTO.from(NOT_FOUND, ErrorsCode.ENTITY_NOT_FOUND, ex.getLocalizedMessage(), null);

        return errorDTO;
    }
}
