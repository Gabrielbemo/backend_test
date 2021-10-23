package com.socios.clube.esportes.models;

import com.socios.clube.esportes.models.enums.Esporte;
import com.socios.clube.esportes.models.enums.StatusInscricao;
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

public class InscricaoTest {
    private Validator validator;

    Inscricao inscricao;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Socio socio = Socio.builder()
                .id(1L)
                .build();

        inscricao = Inscricao.builder()
                .id(1L)
                .esporte(Esporte.BASQUETE)
                .socio(socio)
                .createAt(LocalDateTime.now())
                .statusInscricao(StatusInscricao.DESATIVADA)
                .build();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Set<ConstraintViolation<Inscricao>> violations = validator.validate(inscricao);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setIdWithNull_then_validationsIsNotEmpty(){
        inscricao.setId(null);

        Set<ConstraintViolation<Inscricao>> violations = validator.validate(inscricao);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setSocioWithNull_then_validationsIsNotEmpty(){
        inscricao.setSocio(null);

        Set<ConstraintViolation<Inscricao>> violations = validator.validate(inscricao);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEsporteWithNull_then_validationsIsNotEmpty(){
        inscricao.setEsporte(null);

        Set<ConstraintViolation<Inscricao>> violations = validator.validate(inscricao);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setStatusInscricaoWithNull_then_validationsIsNotEmpty(){
        inscricao.setStatusInscricao(null);

        Set<ConstraintViolation<Inscricao>> violations = validator.validate(inscricao);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setCreateAtWithNull_then_validationsIsNotEmpty(){
        inscricao.setCreateAt(null);

        Set<ConstraintViolation<Inscricao>> violations = validator.validate(inscricao);

        assertFalse(violations.isEmpty());
    }
}
