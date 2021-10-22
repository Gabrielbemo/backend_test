package com.socios.clube.esportes.controllers;

import com.socios.clube.esportes.controllers.dtos.in.CreateSocioRequestDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateSocioResponseDTO;
import com.socios.clube.esportes.controllers.dtos.out.ListSocioResponseDTO;
import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.services.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/socios")
public class SocioController {

    private final SocioService socioService;

    @Autowired
    public SocioController(final SocioService socioService){
        this.socioService = socioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListSocioResponseDTO> getById(@PathVariable final long id){
        return new ResponseEntity<ListSocioResponseDTO>(
                ListSocioResponseDTO.fromEntity(socioService.getById(id)),
                HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ListSocioResponseDTO>> list(){
        return new ResponseEntity<List<ListSocioResponseDTO>>(
                socioService.list().stream().map(socio -> ListSocioResponseDTO.fromEntity(socio)).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CreateSocioResponseDTO> create(@Valid @RequestBody final CreateSocioRequestDTO createSocioRequestDTO){
        Socio socio = socioService.create(createSocioRequestDTO.toEntity());
        return new ResponseEntity<CreateSocioResponseDTO>(
                CreateSocioResponseDTO.fromEntity(socio),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable final Long id, @Valid @RequestBody final CreateSocioRequestDTO updateSocioRequestDTO){
        Socio socio = updateSocioRequestDTO.toEntity();
        socio.setId(id);
        socioService.update(socio);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable final Long id){
        socioService.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
