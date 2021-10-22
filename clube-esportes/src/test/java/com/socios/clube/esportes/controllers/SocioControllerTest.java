package com.socios.clube.esportes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.socios.clube.esportes.controllers.dtos.in.CreateSocioRequestDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateSocioResponseDTO;
import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.services.SocioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void when_createWithEmptyLastName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setLastName("");

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_createWithEmptyEmail_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setEmail("");

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_createWithEmptyPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone("");

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_createWithEmptyAddress_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setAddress("");

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_createWithNullName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setName(null);

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void when_createWithNullLastName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setLastName(null);

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_createWithNullBirthDate_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setBirthDate(null);

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_createWithNullEmail_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setEmail(null);

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_createWithNullPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone(null);

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_createWithNullAddress_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setAddress(null);

        mockMvc.perform(post("/socios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
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

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithEmptyLastName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setLastName("");

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithEmptyEmail_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setEmail("");

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithEmptyPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone("");

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithEmptyAddress_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone("");

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithNullName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setName(null);

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void when_updateWithNullLastName_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setLastName(null);

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithNullBirthDate_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setBirthDate(null);

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithNullEmail_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setEmail(null);

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithNullPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone(null);

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithNullAddress_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setAddress(null);

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_updateWithIncorrectPhone_expect_statusBadRequest() throws Exception {
        createSocioRequestDTO.setPhone("(00)123456-1234");

        mockMvc.perform(put("/socios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSocioRequestDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
