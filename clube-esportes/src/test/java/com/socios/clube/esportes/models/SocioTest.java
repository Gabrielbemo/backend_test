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

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setIdWithNull_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(null)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setNameWithNull_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name(null)
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameWithNull_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName(null)
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setBirthDateWithNull_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(null)
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailWithNull_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email(null)
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setPhoneWithNull_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone(null)
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setAddressWithNull_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address(null)
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setNameEmpty_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setLastNameEmpty_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setEmailEmpty_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("")
                .phone("(00)0000-0000")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setPhoneEmpty_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setAddressEmpty_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(00)0000-0000")
                .address("")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setPhoneIncorrect_then_validationsIsNotEmpty(){
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(12)123456-1234")
                .address("Brazil America do Sul")
                .build();

        Set<ConstraintViolation<Socio>> violations = validator.validate(socio);

        assertFalse(violations.isEmpty());
    }
}
