package com.socios.clube.esportes.controllers;

import com.socios.clube.esportes.controllers.dtos.in.CreateInscricaoRequestDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateInscricaoResponseDTO;
import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.services.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    @Autowired
    public InscricaoController(final InscricaoService inscricaoService){
        this.inscricaoService = inscricaoService;
    }

    @PostMapping("/")
    public ResponseEntity<CreateInscricaoResponseDTO> create(@Valid @RequestBody final CreateInscricaoRequestDTO createInscricaoRequestDTO){
        Inscricao inscricao = inscricaoService.create(createInscricaoRequestDTO.toEntity());
        return new ResponseEntity<CreateInscricaoResponseDTO>(
                CreateInscricaoResponseDTO.fromEntity(inscricao),
                HttpStatus.CREATED);
    }
}
