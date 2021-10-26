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

public class ListInscricaoResponseDTOTest {

    private Validator validator;

    ListInscricaoResponseDTO listInscricaoResponseDTO;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        listInscricaoResponseDTO = ListInscricaoResponseDTO.builder()
                .esporte(Esporte.BASQUETE)
                .statusInscricao(StatusInscricao.DESATIVADA)
                .createAt(LocalDateTime.now())
                .build();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Set<ConstraintViolation<ListInscricaoResponseDTO>> violations = validator.validate(listInscricaoResponseDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setEsporteWithNull_then_validationsIsNotEmpty(){
        listInscricaoResponseDTO.setEsporte(null);

        Set<ConstraintViolation<ListInscricaoResponseDTO>> violations = validator.validate(listInscricaoResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setStatusInscricaoWithNull_then_validationsIsNotEmpty(){
        listInscricaoResponseDTO.setStatusInscricao(null);

        Set<ConstraintViolation<ListInscricaoResponseDTO>> violations = validator.validate(listInscricaoResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setCreateAtWithNull_then_validationsIsNotEmpty(){
        listInscricaoResponseDTO.setCreateAt(null);

        Set<ConstraintViolation<ListInscricaoResponseDTO>> violations = validator.validate(listInscricaoResponseDTO);

        assertFalse(violations.isEmpty());
    }
}
