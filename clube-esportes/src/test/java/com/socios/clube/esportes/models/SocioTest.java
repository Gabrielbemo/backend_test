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

public class SocioTest {
    private Validator validator;

    Socio socio;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        socio = Socio.builder()
                .id(1L)
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
        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setIdWithNull_then_validationsIsNotEmpty(){
        socio.setId(null);

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setNameWithNull_then_validationsIsNotEmpty(){
        socio.setName(null);

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameWithNull_then_validationsIsNotEmpty(){
        socio.setLastName(null);

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setBirthDateWithNull_then_validationsIsNotEmpty(){
        socio.setBirthDate(null);

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailWithNull_then_validationsIsNotEmpty(){
        socio.setEmail(null);

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setPhoneWithNull_then_validationsIsNotEmpty(){
        socio.setPhone(null);

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setAddressWithNull_then_validationsIsNotEmpty(){
        socio.setAddress(null);

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setNameEmpty_then_validationsIsNotEmpty(){
        socio.setName("");

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameEmpty_then_validationsIsNotEmpty(){
        socio.setLastName("");

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailEmpty_then_validationsIsNotEmpty(){
        socio.setEmail("");

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setPhoneEmpty_then_validationsIsNotEmpty(){
        socio.setPhone("");

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setAddressEmpty_then_validationsIsNotEmpty(){
        socio.setAddress("");

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setPhoneIncorrect_then_validationsIsNotEmpty(){
        socio.setPhone("(14)123456-1234");

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }
}
