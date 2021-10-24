package com.socios.clube.esportes.models.enums;

public enum ErrorsCode {
    ERROR_INVALID_ARGUMENTS(0),
    EMPTY_RESULT_DATA_ACCESS(1),
    ENTITY_NOT_FOUND(2),
    PAYMENT_ALREADY_DONE(3);

    private int value;

    ErrorsCode(int code) {
        this.value = code;
    }
}
