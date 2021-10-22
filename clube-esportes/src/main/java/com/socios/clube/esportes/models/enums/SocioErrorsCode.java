package com.socios.clube.esportes.models.enums;

public enum SocioErrorsCode {
    SOCIO_ERROR_INVALID_ARGUMENTS(0),
    SOCIO_EMPTY_RESULT_DATA_ACCESS(1);

    private int value;

    SocioErrorsCode(int code) {
        this.value = code;
    }
}
