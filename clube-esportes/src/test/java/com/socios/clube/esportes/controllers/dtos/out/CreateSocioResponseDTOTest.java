package com.socios.clube.esportes.controllers.dtos.out;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateSocioResponseDTOTest {
    private Validator validator;

    CreateSocioResponseDTO createSocioResponseDTO;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        createSocioResponseDTO = CreateSocioResponseDTO.builder()
                .name("gabriel")
                .lastName("moura")
                .email("gabriel123@gmail.com")
                .build();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Set<ConstraintViolation<CreateSocioResponseDTO>> violations = validator.validate(createSocioResponseDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setNameWithNull_then_validationsIsNotEmpty(){
        createSocioResponseDTO.setName(null);

        Set<ConstraintViolation<CreateSocioResponseDTO>> violations = validator.validate(createSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameWithNull_then_validationsIsNotEmpty(){
        createSocioResponseDTO.setLastName(null);

        Set<ConstraintViolation<CreateSocioResponseDTO>> violations = validator.validate(createSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailWithNull_then_validationsIsNotEmpty(){
        createSocioResponseDTO.setEmail(null);

        Set<ConstraintViolation<CreateSocioResponseDTO>> violations = validator.validate(createSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setNameEmpty_then_validationsIsNotEmpty(){
        createSocioResponseDTO.setName("");

        Set<ConstraintViolation<CreateSocioResponseDTO>> violations = validator.validate(createSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameEmpty_then_validationsIsNotEmpty(){
        createSocioResponseDTO.setLastName("");

        Set<ConstraintViolation<CreateSocioResponseDTO>> violations = validator.validate(createSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailEmpty_then_validationsIsNotEmpty(){
        createSocioResponseDTO.setEmail("");

        Set<ConstraintViolation<CreateSocioResponseDTO>> violations = validator.validate(createSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }
}
