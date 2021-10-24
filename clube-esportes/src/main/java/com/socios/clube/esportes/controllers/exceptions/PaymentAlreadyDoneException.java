package com.socios.clube.esportes.controllers.exceptions;

public class PaymentAlreadyDoneException extends Exception{
    public PaymentAlreadyDoneException(String errorMessage) {
        super(errorMessage);
    }
}
