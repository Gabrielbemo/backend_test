package com.socios.clube.esportes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.socios.clube.esportes.controllers.dtos.in.CreateInscricaoRequestDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateInscricaoPagarResponseDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateInscricaoResponseDTO;
import com.socios.clube.esportes.controllers.dtos.out.ErrorDTO;
import com.socios.clube.esportes.controllers.exceptions.PaymentAlreadyDoneException;
import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.PagamentoInscricao;
import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.models.enums.Esporte;
import com.socios.clube.esportes.models.enums.ErrorsCode;
import com.socios.clube.esportes.models.enums.StatusInscricao;
import com.socios.clube.esportes.services.InscricaoService;
import com.socios.clube.esportes.services.PagamentoInscricaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class InscricaoControllerTest {

    @InjectMocks
    private InscricaoController inscricaoController;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private InscricaoService inscricaoService;

    @Mock
    private PagamentoInscricaoService pagamentoInscricaoService;

    CreateInscricaoRequestDTO createInscricaoRequestDTO;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(inscricaoController)
                .setControllerAdvice(new ExceptionHandle())
                .build();

        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());


        createInscricaoRequestDTO = CreateInscricaoRequestDTO.builder()
                .esporte(Esporte.BASQUETE)
                .socioId(1L)
                .build();

    }

    @Test
    public void when_createWithSuccess_expect_statusCreated() throws Exception {
        Inscricao inscricao = createInscricaoRequestDTO.toEntity();

        when(inscricaoService.create(isA(Inscricao.class)))
                .thenReturn(inscricao);

        MvcResult result = mockMvc.perform(post("/inscricoes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createInscricaoRequestDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        CreateInscricaoResponseDTO inscricaoResult = objectMapper.readValue(result.getResponse().getContentAsString(), CreateInscricaoResponseDTO.class);

        Assertions.assertEquals(inscricaoResult.getEsporte(), inscricao.getEsporte());
        Assertions.assertEquals(inscricaoResult.getStatusInscricao(), inscricao.getStatusInscricao());
    }

    @Test
    public void when_createWithNullEsporte_expect_statusBadRequest() throws Exception {
        createInscricaoRequestDTO.setEsporte(null);

        MvcResult result = mockMvc.perform(post("/inscricoes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createInscricaoRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorDTO errorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorDTO.class);

        Assertions.assertEquals(errorDTO.getError(), ErrorsCode.ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(errorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(errorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithNullSocio_expect_statusBadRequest() throws Exception {
        createInscricaoRequestDTO.setSocioId(null);

        MvcResult result = mockMvc.perform(post("/inscricoes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createInscricaoRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorDTO errorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorDTO.class);

        Assertions.assertEquals(errorDTO.getError(), ErrorsCode.ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(errorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(errorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_getByIdWithSuccess_expect_statusOk() throws Exception {


        Inscricao inscricao = Inscricao.builder()
                .socio(new Socio(1L))
                .esporte(Esporte.BASQUETE)
                .statusInscricao(StatusInscricao.DESATIVADA)
                .createAt(LocalDateTime.now())
                .build();

        when(inscricaoService.getById(1L)).thenReturn(inscricao);

        mockMvc.perform(get("/inscricoes/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void when_getByIdWithInvalidId_expect_statusNotFound() throws Exception {
        when(inscricaoService.getById(0L)).thenThrow(new EntityNotFoundException());

        MvcResult result = mockMvc.perform(get("/inscricoes/0"))
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorDTO errorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorDTO.class);

        Assertions.assertEquals(errorDTO.getError(), ErrorsCode.ENTITY_NOT_FOUND);
        Assertions.assertEquals(errorDTO.getCode(), HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void when_listWithSuccess_expect_statusOk() throws Exception {
        List<Inscricao> inscricoes = new ArrayList<Inscricao>();

        LocalDateTime actualDate = LocalDateTime.now();

        Inscricao inscricao1 = Inscricao.builder()
                .socio(new Socio(1L))
                .esporte(Esporte.FUTEBOL)
                .statusInscricao(StatusInscricao.ATIVA)
                .createAt(actualDate)
                .build();

        Inscricao inscricao2 = Inscricao.builder()
                .socio(new Socio(2L))
                .esporte(Esporte.BASQUETE)
                .statusInscricao(StatusInscricao.DESATIVADA)
                .createAt(actualDate)
                .build();

        inscricoes.add(inscricao1);
        inscricoes.add(inscricao2);

        when(inscricaoService.list()).thenReturn(inscricoes);

        MvcResult result = mockMvc.perform(get("/inscricoes/"))
                .andDo(print())
                .andExpect(jsonPath("$[0].esporte", equalTo(Esporte.FUTEBOL.name())))
                .andExpect(jsonPath("$[0].statusInscricao", equalTo(StatusInscricao.ATIVA.name())))
                .andExpect(jsonPath("$[1].esporte", equalTo(Esporte.BASQUETE.name())))
                .andExpect(jsonPath("$[1].statusInscricao", equalTo(StatusInscricao.DESATIVADA.name())))
                .andExpect(jsonPath("$.length()", equalTo(2)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void when_payWithSuccess_expect_statusCreated() throws Exception {
        Inscricao inscricao = Inscricao.builder()
                .id(1L)
                .build();

        LocalDateTime actualDate = LocalDateTime.now();

        PagamentoInscricao pagamentoInscricao = PagamentoInscricao.builder()
                .inscricao(inscricao)
                .createAt(actualDate)
                .build();

        when(pagamentoInscricaoService.payInscricao(1L))
                .thenReturn(pagamentoInscricao);

        MvcResult result = mockMvc.perform(post("/inscricoes/1/pagar"))
                .andExpect(status().isCreated())
                .andReturn();

        CreateInscricaoPagarResponseDTO inscricaoPagarResponse = objectMapper.readValue(result.getResponse().getContentAsString(), CreateInscricaoPagarResponseDTO.class);

        Assertions.assertEquals(inscricaoPagarResponse.getInscricao().getId(), inscricao.getId());
        Assertions.assertEquals(inscricaoPagarResponse.getCreateAt(), actualDate);
    }

    @Test
    public void when_payAPaymentAlreadyDone_expect_statusBadRequest() throws Exception {
        when(pagamentoInscricaoService.payInscricao(1L))
                .thenThrow(new PaymentAlreadyDoneException("Payment already done on this date"));

        MvcResult result = mockMvc.perform(post("/inscricoes/1/pagar"))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorDTO errorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorDTO.class);

        Assertions.assertEquals(errorDTO.getError(), ErrorsCode.PAYMENT_ALREADY_DONE);
        Assertions.assertEquals(errorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_payWithInvalidId_expect_statusBadRequest() throws Exception {
        when(pagamentoInscricaoService.payInscricao(0L))
                .thenThrow(new EntityNotFoundException(String.format("Unable to find com.socios.clube.esportes.models.Inscricao with id %s", 0)));

        MvcResult result = mockMvc.perform(post("/inscricoes/0/pagar"))
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorDTO errorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorDTO.class);

        Assertions.assertEquals(errorDTO.getError(), ErrorsCode.ENTITY_NOT_FOUND);
        Assertions.assertEquals(errorDTO.getCode(), HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void when_checkWithSuccess_expect_statusNoContent() throws Exception {
        doNothing().when(pagamentoInscricaoService).checkPayments(1L);

        mockMvc.perform(put("/inscricoes/1/check"))
                .andExpect(status().isNoContent());
    }
}
