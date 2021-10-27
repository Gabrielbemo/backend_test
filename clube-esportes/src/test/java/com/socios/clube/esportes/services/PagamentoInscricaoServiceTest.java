package com.socios.clube.esportes.services;

import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.PagamentoInscricao;
import com.socios.clube.esportes.repositories.InscricaoRepository;
import com.socios.clube.esportes.repositories.PagamentoInscricaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagamentoInscricaoServiceTest {
    @InjectMocks
    private PagamentoInscricaoService pagamentoInscricaoService;

    @Mock
    private PagamentoInscricaoRepository pagamentoInscricaoRepository;

    @Mock
    private InscricaoRepository inscricaoRepository;

    PagamentoInscricao pagamentoInscricao;
    Inscricao inscricao;

    @BeforeEach
    public void setup(){
        inscricao = Inscricao.builder()
                .id(1L)
                .build();

        pagamentoInscricao = PagamentoInscricao.builder()
                .id(1L)
                .inscricao(inscricao)
                .createAt(LocalDateTime.now())
                .build();
    }

    @Test
    public void when_createWithSuccess_then_doesNotReturnException() {
        when(inscricaoRepository.findById(1L)).thenReturn(Optional.of(inscricao));
        List<PagamentoInscricao> pagamentoInscricoes = new ArrayList<PagamentoInscricao>();
        when(pagamentoInscricaoRepository.findByMonthAndYear(1L, LocalDateTime.now().getMonth().getValue(), LocalDateTime.now().getYear())).thenReturn(Optional.of(pagamentoInscricoes));
        when(pagamentoInscricaoRepository.save(isA(PagamentoInscricao.class))).thenReturn(null);
        when(pagamentoInscricaoRepository.getById(null)).thenReturn(pagamentoInscricao);

        assertDoesNotThrow(() -> {
            PagamentoInscricao pagamentoInscricaoCreated = pagamentoInscricaoService.payInscricao(1L);

            assertEquals(pagamentoInscricao, pagamentoInscricaoCreated);

        });

        verify(inscricaoRepository).findById(1L);
        verify(pagamentoInscricaoRepository).findByMonthAndYear(1L, LocalDateTime.now().getMonth().getValue(), LocalDateTime.now().getYear());
    }

    @Test
    public void when_checkPaymentsWithSuccess_then_doesNotReturnException() {
        when(inscricaoRepository.findById(0L)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            pagamentoInscricaoService.checkPayments(0L);
        });

        verify(inscricaoRepository).findById(0L);
    }
}
