package com.socios.clube.esportes.services;

import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.models.enums.Esporte;
import com.socios.clube.esportes.models.enums.StatusInscricao;
import com.socios.clube.esportes.repositories.InscricaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InscricaoServiceTest {

    @InjectMocks
    private InscricaoService inscricaoService;

    @Mock
    private InscricaoRepository inscricaoRepository;

    Inscricao inscricao;

    @BeforeEach
    public void setup(){
        Socio socio = Socio.builder()
                .id(1L)
                .build();

        inscricao = Inscricao.builder()
                .id(1L)
                .esporte(Esporte.BASQUETE)
                .socio(socio)
                .createAt(LocalDateTime.now())
                .statusInscricao(StatusInscricao.DESATIVADA)
                .build();
    }

    @Test
    public void when_createWithSuccess_then_doesNotReturnException() {
        when(inscricaoRepository.save(inscricao)).thenReturn(null);
        when(inscricaoRepository.getById(inscricao.getId())).thenReturn(inscricao);

        assertDoesNotThrow(() -> {
            Inscricao inscricaoCreated = inscricaoService.create(inscricao);

            assertEquals(inscricao, inscricaoCreated);

        });

        verify(inscricaoRepository).save(inscricao);
        verify(inscricaoRepository).getById(inscricao.getId());
    }

    @Test
    public void when_createWithNullInscricao_then_returnException() {
        assertThrows( RuntimeException.class, () -> inscricaoService.create(null));
    }

    @Test
    public void when_createWithNullEsporte_then_returnException() {
        inscricao.setEsporte(null);

        when(inscricaoRepository.save(isA(Inscricao.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> inscricaoService.create(inscricao));

        verify(inscricaoRepository).save(isA(Inscricao.class));
    }

    @Test
    public void when_createWithNullSocio_then_returnException() {
        inscricao.setSocio(null);

        when(inscricaoRepository.save(isA(Inscricao.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> inscricaoService.create(inscricao));

        verify(inscricaoRepository).save(isA(Inscricao.class));
    }

    @Test
    public void when_createWithNullStatusInscricao_then_returnException() {
        inscricao.setStatusInscricao(null);

        when(inscricaoRepository.save(isA(Inscricao.class))).thenReturn(inscricao);

        assertDoesNotThrow(() -> inscricaoService.create(inscricao));

        verify(inscricaoRepository).save(isA(Inscricao.class));
    }

    @Test
    public void when_createWithNullCreateAt_then_returnException() {
        inscricao.setCreateAt(null);

        when(inscricaoRepository.save(isA(Inscricao.class))).thenReturn(inscricao);

        assertDoesNotThrow(() -> inscricaoService.create(inscricao));

        verify(inscricaoRepository).save(isA(Inscricao.class));
    }
}
