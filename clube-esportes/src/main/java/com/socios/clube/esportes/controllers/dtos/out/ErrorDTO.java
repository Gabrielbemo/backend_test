package com.socios.clube.esportes.controllers.dtos.out;

import com.socios.clube.esportes.models.enums.ErrorsCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private int code;
    private ErrorsCode error;
    private String message;
    private List<ErrorFieldDTO> errorFieldDTO;

    public static ErrorDTO from(final HttpStatus httpStatus, final ErrorsCode osErrorCode, final String message, final List<ErrorFieldDTO> errorFieldDTO){
        return ErrorDTO.builder()
                .code(httpStatus.value())
                .error(osErrorCode)
                .message(message)
                .errorFieldDTO(errorFieldDTO)
                .build();
    }
}
