package com.socios.clube.esportes.services;

import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.repositories.SocioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SocioServiceTest {

    @InjectMocks
    private SocioService socioService;

    @Mock
    private SocioRepository socioRepository;

    Socio socio;

    @BeforeEach
    public void setup(){
        socio = Socio.builder()
                .id(1L)
                .name("gabriel")
                .lastName("moura")
                .birthDate(LocalDateTime.now())
                .email("gabriel123@gmail.com")
                .phone("(12)123456-1234")
                .address("Brazil America do Sul")
                .build();
    }

    @Test
    public void when_createWithSuccess_then_doesNotReturnException() {
        when(socioRepository.save(socio)).thenReturn(null);
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        assertDoesNotThrow(() -> {
            Socio socioCreated = socioService.create(socio);

            assertEquals(socio, socioCreated);

        });

        verify(socioRepository).save(socio);
        verify(socioRepository).getById(socio.getId());
    }

    @Test
    public void when_createWithNullSocio_then_returnException() {
        when(socioRepository.save(null)).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(null));

        verify(socioRepository).save(null);
    }

    @Test
    public void when_createWithEmptyName_then_returnException() {
        socio.setName("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithEmptyLastName_then_returnException() {
        socio.setLastName("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithEmptyEmail_then_returnException() {
        socio.setEmail("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithEmptyPhone_then_returnException() {
        socio.setPhone("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithEmptyAddress_then_returnException() {
        socio.setAddress("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithNullName_then_returnException() {
        socio.setName(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithNullLastName_then_returnException() {
        socio.setLastName(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithNullEmail_then_returnException() {
        socio.setEmail(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithNullPhone_then_returnException() {
        socio.setPhone(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithNullBirthDate_then_returnException() {
        socio.setBirthDate(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_createWithNullAddress_then_returnException() {
        socio.setAddress(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.create(socio));

        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithSuccess_then_doesNotReturnException() {
        when(socioRepository.save(socio)).thenReturn(null);
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        assertDoesNotThrow(() -> socioService.update(socio));

        verify(socioRepository).save(socio);
        verify(socioRepository).getById(socio.getId());
    }

    @Test
    public void when_updateWithEmptyName_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setName("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithEmptyLastName_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setLastName("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithEmptyEmail_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setEmail("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithEmptyPhone_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setPhone("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithEmptyAddress_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setAddress("");

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithNullName_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setName(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithNullLastName_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setLastName(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithNullEmail_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setEmail(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithNullPhone_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setPhone(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithNullBirthDate_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setBirthDate(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_updateWithNullAddress_then_returnException() {
        when(socioRepository.getById(socio.getId())).thenReturn(socio);

        socio.setAddress(null);

        when(socioRepository.save(isA(Socio.class))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> socioService.update(socio));

        verify(socioRepository).getById(socio.getId());
        verify(socioRepository).save(isA(Socio.class));
    }

    @Test
    public void when_deleteWithSuccess_then_doesNotReturnException() {
        doNothing().when(socioRepository).deleteById(socio.getId());

        assertDoesNotThrow(() -> {
            socioService.deleteById(socio.getId());
        });

        verify(socioRepository).deleteById(socio.getId());
    }

    @Test
    public void when_deleteWithId0_then_returnException() {
        doThrow(new EmptyResultDataAccessException(String.format("No class com.socios.clube.esportes.models.Socio entity with id %s exists!", 0), 1))
                .when(socioRepository)
                .deleteById(0L);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            socioService.deleteById(0L);
        });

        verify(socioRepository).deleteById(0L);
    }
}
