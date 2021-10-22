package com.socios.clube.esportes.controllers.dtos.in;

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

public class CreateSocioRequestDTOTest {

    private Validator validator;

    CreateSocioRequestDTO createSocioRequestDTO;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        createSocioRequestDTO = CreateSocioRequestDTO.builder()
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setNameWithNull_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setName(null);

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameWithNull_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setLastName(null);

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setBirthDateWithNull_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setBirthDate(null);

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailWithNull_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setEmail(null);

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setPhoneWithNull_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setPhone(null);

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setAddressWithNull_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setAddress(null);

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setNameEmpty_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setName("");

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameEmpty_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setLastName("");

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailEmpty_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setEmail("");

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setPhoneEmpty_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setPhone("");

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setAddressEmpty_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setAddress("");

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setPhoneIncorrect_then_validationsIsNotEmpty(){
        createSocioRequestDTO.setPhone("(00)123456-1234");

        Set<ConstraintViolation<CreateSocioRequestDTO>> violations = validator.validate(createSocioRequestDTO);

        assertFalse(violations.isEmpty());
    }
}
