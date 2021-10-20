package com.socios.clube.esportes.services;

import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.repositories.SocioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SocioServiceTest {

    @InjectMocks
    private SocioService socioService;

    @Mock
    private SocioRepository socioRepository;

    @Test
    public void when_createWithSuccess_then_returnCreated() throws Exception {
        Socio socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(12)123456-1234")
                .address("Brazil America do Sul")
                .build();

        when(socioRepository.save(socio)).thenReturn(null);
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        assertDoesNotThrow(() -> {
            Socio socioCreated = socioService.create(socio);

            assertTrue(socio.equals(socioCreated));

        });

        verify(socioRepository).save(socio);
        verify(socioRepository).getById(socio.getId());
    }

    @Test
    public void when_createWithInvalidField_then_returnCreated() throws Exception {
        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> {
            socioService.create(new Socio());
        });

        verify(socioRepository).save(isA(Socio.class));
    }
}
