package com.socios.clube.esportes.models.enums;

public enum StatusInscricao {
    ATIVA(0),
    DESATIVADA(1);

    private int value;

    StatusInscricao(int code) {
        this.value = code;
    }
}
