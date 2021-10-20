package com.socios.clube.esportes.controllers;

import com.socios.clube.esportes.controllers.dtos.in.CreateSocioRequestDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateSocioResponseDTO;
import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.services.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/socios")
public class SocioController {

    private final SocioService socioService;

    @Autowired
    public SocioController(final SocioService socioService){
        this.socioService = socioService;
    }

    @PostMapping("/")
    public ResponseEntity<CreateSocioResponseDTO> create(@Valid @RequestBody final CreateSocioRequestDTO createSocioRequestDTO){
        Socio socio = socioService.create(createSocioRequestDTO.toEntity());
        return new ResponseEntity<CreateSocioResponseDTO>(
                CreateSocioResponseDTO.fromEntity(socio),
                HttpStatus.CREATED);
    }
}
