package com.socios.clube.esportes.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PagamentoInscricaoTest {
    private Validator validator;

    PagamentoInscricao pagamentoInscricao;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Inscricao inscricao = Inscricao.builder()
                .id(1L)
                .build();

        pagamentoInscricao = PagamentoInscricao.builder()
                .id(1L)
                .inscricao(inscricao)
                .createAt(LocalDateTime.now())
                .build();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Set<ConstraintViolation<PagamentoInscricao>> violations = validator.validate(pagamentoInscricao);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setIdWithNull_then_validationsIsNotEmpty(){
        pagamentoInscricao.setId(null);

        Set<ConstraintViolation<PagamentoInscricao>> violations = validator.validate(pagamentoInscricao);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setInscricaoWithNull_then_validationsIsNotEmpty(){
        pagamentoInscricao.setInscricao(null);

        Set<ConstraintViolation<PagamentoInscricao>> violations = validator.validate(pagamentoInscricao);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setCreateAtWithNull_then_validationsIsNotEmpty(){
        pagamentoInscricao.setCreateAt(null);

        Set<ConstraintViolation<PagamentoInscricao>> violations = validator.validate(pagamentoInscricao);

        assertFalse(violations.isEmpty());
    }
}
