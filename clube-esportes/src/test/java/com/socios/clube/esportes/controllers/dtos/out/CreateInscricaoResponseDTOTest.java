package com.socios.clube.esportes.controllers.dtos.out;

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

public class CreateInscricaoResponseDTOTest {
    private Validator validator;

    CreateInscricaoResponseDTO createInscricaoResponseDTO;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        createInscricaoResponseDTO = CreateInscricaoResponseDTO.builder()
                .esporte(Esporte.BASQUETE)
                .createAt(LocalDateTime.now())
                .statusInscricao(StatusInscricao.DESATIVADA)
                .build();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Set<ConstraintViolation<CreateInscricaoResponseDTO>> violations = validator.validate(createInscricaoResponseDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setEsporteWithNull_then_validationsIsNotEmpty(){
        createInscricaoResponseDTO.setEsporte(null);

        Set<ConstraintViolation<CreateInscricaoResponseDTO>> violations = validator.validate(createInscricaoResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setCreateAtWithNull_then_validationsIsNotEmpty(){
        createInscricaoResponseDTO.setCreateAt(null);

        Set<ConstraintViolation<CreateInscricaoResponseDTO>> violations = validator.validate(createInscricaoResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setStatusInscricaoWithNull_then_validationsIsNotEmpty(){
        createInscricaoResponseDTO.setStatusInscricao(null);

        Set<ConstraintViolation<CreateInscricaoResponseDTO>> violations = validator.validate(createInscricaoResponseDTO);

        assertFalse(violations.isEmpty());
    }
}
