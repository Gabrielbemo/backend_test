package com.socios.clube.esportes.controllers.dtos.in;

import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.models.enums.Esporte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateInscricaoRequestDTOTest {

    private Validator validator;

    CreateInscricaoRequestDTO createInscricaoRequestDTO;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        createInscricaoRequestDTO = CreateInscricaoRequestDTO.builder()
                .esporte(Esporte.BASQUETE)
                .socioId(1L)
                .build();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Set<ConstraintViolation<CreateInscricaoRequestDTO>> violations = validator.validate(createInscricaoRequestDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setEsporteWithNull_then_validationsIsNotEmpty(){
        createInscricaoRequestDTO.setEsporte(null);

        Set<ConstraintViolation<CreateInscricaoRequestDTO>> violations = validator.validate(createInscricaoRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setSocioWithNull_then_validationsIsNotEmpty(){
        createInscricaoRequestDTO.setSocioId(null);

        Set<ConstraintViolation<CreateInscricaoRequestDTO>> violations = validator.validate(createInscricaoRequestDTO);

        assertFalse(violations.isEmpty());
    }
}
