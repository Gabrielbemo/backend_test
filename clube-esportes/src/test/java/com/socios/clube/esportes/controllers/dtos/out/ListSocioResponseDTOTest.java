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

public class ListSocioResponseDTOTest {
    private Validator validator;

    ListSocioResponseDTO listSocioResponseDTO;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        listSocioResponseDTO = ListSocioResponseDTO.builder()
                .name("gabriel")
                .lastName("moura")
                .email("gabriel123@gmail.com")
                .build();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Set<ConstraintViolation<ListSocioResponseDTO>> violations = validator.validate(listSocioResponseDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setNameWithNull_then_validationsIsNotEmpty(){
        listSocioResponseDTO.setName(null);

        Set<ConstraintViolation<ListSocioResponseDTO>> violations = validator.validate(listSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameWithNull_then_validationsIsNotEmpty(){
        listSocioResponseDTO.setLastName(null);

        Set<ConstraintViolation<ListSocioResponseDTO>> violations = validator.validate(listSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailWithNull_then_validationsIsNotEmpty(){
        listSocioResponseDTO.setEmail(null);

        Set<ConstraintViolation<ListSocioResponseDTO>> violations = validator.validate(listSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setNameEmpty_then_validationsIsNotEmpty(){
        listSocioResponseDTO.setName("");

        Set<ConstraintViolation<ListSocioResponseDTO>> violations = validator.validate(listSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameEmpty_then_validationsIsNotEmpty(){
        listSocioResponseDTO.setLastName("");

        Set<ConstraintViolation<ListSocioResponseDTO>> violations = validator.validate(listSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailEmpty_then_validationsIsNotEmpty(){
        listSocioResponseDTO.setEmail("");

        Set<ConstraintViolation<ListSocioResponseDTO>> violations = validator.validate(listSocioResponseDTO);

        assertFalse(violations.isEmpty());
    }
}
