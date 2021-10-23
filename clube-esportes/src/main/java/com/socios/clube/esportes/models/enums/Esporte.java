package com.socios.clube.esportes.models.enums;

public enum Esporte {
    NATACAO(0),
    JIUJITSU(1),
    KARATE(2),
    BASQUETE(3),
    FUTEBOL(4);

    private int value;

    Esporte(int code) {
        this.value = code;
    }
}
