package com.socios.clube.esportes.controllers;

import com.socios.clube.esportes.controllers.dtos.out.ErrorFieldDTO;
import com.socios.clube.esportes.controllers.dtos.out.SocioErrorDTO;
import com.socios.clube.esportes.models.enums.SocioErrorsCode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SocioErrorDTO handlerMethodArgumentNotValidException(final MethodArgumentNotValidException ex){
        List<ErrorFieldDTO> errorFieldDTO = ex
                .getAllErrors()
                .stream()
                .map(error -> (FieldError)error)
                .map(ErrorFieldDTO::fromFieldError)
                .collect(Collectors.toList());

        SocioErrorDTO socioErrorDTO = SocioErrorDTO.from(BAD_REQUEST, SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS,"Invalid Arguments", errorFieldDTO);

        return socioErrorDTO;
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    @ResponseStatus(NOT_FOUND)
    public SocioErrorDTO handlerEmptyResultDataAccessException(final EmptyResultDataAccessException ex){

        SocioErrorDTO socioErrorDTO = SocioErrorDTO.from(NOT_FOUND, SocioErrorsCode.SOCIO_EMPTY_RESULT_DATA_ACCESS, ex.getLocalizedMessage(), null);

        return socioErrorDTO;
    }
}
