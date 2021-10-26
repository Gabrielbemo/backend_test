package com.socios.clube.esportes.controllers.dtos.out;

import com.socios.clube.esportes.models.Inscricao;
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

public class CreateInscricaoPagarResponseDTOTest {
    private Validator validator;

    CreateInscricaoPagarResponseDTO createInscricaoPagarResponseDTO;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Inscricao inscricao = Inscricao.builder()
                .id(1L)
                .build();

        createInscricaoPagarResponseDTO = CreateInscricaoPagarResponseDTO.builder()
                .inscricao(inscricao)
                .createAt(LocalDateTime.now())
                .build();
    }

    @Test
    public void when_validationsPassed_then_validationsIsEmpty(){
        Set<ConstraintViolation<CreateInscricaoPagarResponseDTO>> violations = validator.validate(createInscricaoPagarResponseDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void when_setInscricaoWithNull_then_validationsIsNotEmpty(){
        createInscricaoPagarResponseDTO.setInscricao(null);

        Set<ConstraintViolation<CreateInscricaoPagarResponseDTO>> violations = validator.validate(createInscricaoPagarResponseDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void when_setCreateAtWithNull_then_validationsIsNotEmpty(){
        createInscricaoPagarResponseDTO.setCreateAt(null);

        Set<ConstraintViolation<CreateInscricaoPagarResponseDTO>> violations = validator.validate(createInscricaoPagarResponseDTO);

        assertFalse(violations.isEmpty());
    }
}
