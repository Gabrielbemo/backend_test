package com.socios.clube.esportes.controllers.dtos.out;

import com.socios.clube.esportes.models.enums.SocioErrorsCode;
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
public class SocioErrorDTO {
    private int code;
    private SocioErrorsCode error;
    private String message;
    private List<ErrorFieldDTO> errorFieldDTO;

    public static SocioErrorDTO from(final HttpStatus httpStatus, final SocioErrorsCode osErrorCode, final String message, final List<ErrorFieldDTO> errorFieldDTO){
        return SocioErrorDTO.builder()
                .code(httpStatus.value())
                .error(osErrorCode)
                .message(message)
                .errorFieldDTO(errorFieldDTO)
                .build();
    }
}
