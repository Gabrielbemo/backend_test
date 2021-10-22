package com.socios.clube.esportes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.socios.clube.esportes.controllers.dtos.in.CreateSocioRequestDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateSocioResponseDTO;
import com.socios.clube.esportes.controllers.dtos.out.SocioErrorDTO;
import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.models.enums.SocioErrorsCode;
import com.socios.clube.esportes.services.SocioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class SocioControllerTest {

    @InjectMocks
    private SocioController socioController;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private SocioService socioService;

    CreateSocioRequestDTO createSocioRequestDTO;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(socioController)
                .setControllerAdvice(new ExceptionHandle())
                .build();

        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

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
    public void when_createWithSuccess_expect_statusCreated() throws Exception {
        Socio socio = createSocioRequestDTO.toEntity();

        when(socioService.create(isA(Socio.class)))
                .thenReturn(socio);

        MvcResult result = mockMvc.perform(post("/socios/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        CreateSocioResponseDTO socioResult = objectMapper.readValue(result.getResponse().getContentAsString(), CreateSocioResponseDTO.class);

        Assertions.assertEquals(socioResult.getName(), socio.getName());
        Assertions.assertEquals(socioResult.getLastName(), socio.getLastName());
        Assertions.assertEquals(socioResult.getEmail(), socio.getEmail());
    }

    @Test
    public void when_createWithEmptyName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setName("");

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithEmptyLastName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setLastName("");

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithEmptyEmail_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setEmail("");

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithEmptyPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone("");

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithEmptyAddress_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setAddress("");

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithNullName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setName(null);

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void when_createWithNullLastName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setLastName(null);

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithNullBirthDate_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setBirthDate(null);

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithNullEmail_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setEmail(null);

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithNullPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone(null);

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_createWithNullAddress_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setAddress(null);

        MvcResult result = mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithSuccess_expect_statusNoContent() throws Exception {
        doNothing().when(socioService).update(isA(Socio.class));

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void when_updateWithEmptyName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setName("");

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithEmptyLastName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setLastName("");

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithEmptyEmail_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setEmail("");

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithEmptyPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone("");

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithEmptyAddress_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone("");

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithNullName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setName(null);

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithNullLastName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setLastName(null);

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithNullBirthDate_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setBirthDate(null);

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithNullEmail_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setEmail(null);

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithNullPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone(null);

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithNullAddress_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setAddress(null);

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 2);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_updateWithIncorrectPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone("(00)123456-1234");

        MvcResult result = mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_ERROR_INVALID_ARGUMENTS);
        Assertions.assertEquals(socioErrorDTO.getErrorFieldDTO().size(), 1);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void when_deleteWithSuccess_expect_statusNoContent() throws Exception {
        doNothing().when(socioService).deleteById(1L);

        mockMvc.perform(delete("/socios/1"))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void when_deleteWithId0_expect_throwException() throws Exception {
        doThrow(new EmptyResultDataAccessException(String.format("No class com.socios.clube.esportes.models.Socio entity with id %s exists!", 0), 1))
                .when(socioService)
                .deleteById(0L);

        MvcResult result = mockMvc.perform(delete("/socios/0"))
                .andExpect(status().isNotFound())
                .andReturn();

        SocioErrorDTO socioErrorDTO = objectMapper.readValue(result.getResponse().getContentAsString(), SocioErrorDTO.class);

        Assertions.assertEquals(socioErrorDTO.getError(), SocioErrorsCode.SOCIO_EMPTY_RESULT_DATA_ACCESS);
        Assertions.assertEquals(socioErrorDTO.getCode(), HttpStatus.NOT_FOUND.value());
    }
}
