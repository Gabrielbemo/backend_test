package com.socios.clube.esportes.controllers.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorFieldDTO {
    private String field;
    private String message;

    public static ErrorFieldDTO fromFieldError(final FieldError errorField) {
        return ErrorFieldDTO.builder()
                .field(errorField.getField())
                .message(errorField.getDefaultMessage())
                .build();
    }
}
