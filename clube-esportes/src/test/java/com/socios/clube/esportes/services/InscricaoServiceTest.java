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

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InscricaoServiceTest {

    @InjectMocks
    private InscricaoService inscricaoService;

    @Mock
    private InscricaoRepository inscricaoRepository;

    @Mock
    private PagamentoInscricaoService pagamentoInscricaoService;

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

    @Test
    public void when_getByIdWithSuccess_then_doesNotReturnException() {
        doNothing().when(pagamentoInscricaoService).checkPayments(1L);
        when(inscricaoRepository.getById(inscricao.getId())).thenReturn(inscricao);

        assertDoesNotThrow(() -> {
            Inscricao inscricaoCreated = inscricaoService.getById(inscricao.getId());

            assertEquals(inscricao, inscricaoCreated);

        });

        verify(inscricaoRepository).getById(inscricao.getId());
    }

    @Test
    public void when_getByIdWithInvalidId_then_doesNotReturnException() {
        doNothing().when(pagamentoInscricaoService).checkPayments(0L);
        when(inscricaoRepository.getById(0L)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> inscricaoService.getById(0L));

        verify(inscricaoRepository).getById(0L);
    }

    @Test
    public void when_getListWithSuccess_then_doesNotReturnException() {
        doNothing().when(pagamentoInscricaoService).checkPayments(inscricao.getId());
        List<Inscricao> inscricaoList = new ArrayList<>();

        inscricaoList.add(inscricao);

        when(inscricaoRepository.findAll()).thenReturn(inscricaoList);

        assertDoesNotThrow(() -> {
            List<Inscricao> inscricaoListReturned = inscricaoService.list();

            assertEquals(inscricaoListReturned, inscricaoList);

        });

        verify(inscricaoRepository).findAll();
    }
}
