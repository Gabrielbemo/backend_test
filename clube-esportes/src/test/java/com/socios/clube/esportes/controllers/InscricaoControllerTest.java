package com.socios.clube.esportes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.socios.clube.esportes.controllers.dtos.in.CreateInscricaoRequestDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateInscricaoResponseDTO;
import com.socios.clube.esportes.controllers.dtos.out.ErrorDTO;
import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.models.enums.Esporte;
import com.socios.clube.esportes.models.enums.ErrorsCode;
import com.socios.clube.esportes.services.InscricaoService;
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

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    CreateInscricaoRequestDTO createInscricaoRequestDTO;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(inscricaoController)
                .setControllerAdvice(new ExceptionHandle())
                .build();

        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        Socio socio = Socio.builder()
                .id(1L)
                .build();

        createInscricaoRequestDTO = CreateInscricaoRequestDTO.builder()
                .esporte(Esporte.BASQUETE)
                .socio(socio)
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
        createInscricaoRequestDTO.setSocio(null);

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
}
