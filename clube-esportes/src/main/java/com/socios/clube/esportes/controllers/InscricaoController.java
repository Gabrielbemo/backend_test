package com.socios.clube.esportes.controllers;

import com.socios.clube.esportes.controllers.dtos.in.CreateInscricaoRequestDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateInscricaoPagarResponseDTO;
import com.socios.clube.esportes.controllers.dtos.out.CreateInscricaoResponseDTO;
import com.socios.clube.esportes.controllers.dtos.out.ListInscricaoResponseDTO;
import com.socios.clube.esportes.controllers.exceptions.PaymentAlreadyDoneException;
import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.PagamentoInscricao;
import com.socios.clube.esportes.services.InscricaoService;
import com.socios.clube.esportes.services.PagamentoInscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;
    private final PagamentoInscricaoService pagamentoInscricaoService;

    @Autowired
    public InscricaoController(final InscricaoService inscricaoService, final PagamentoInscricaoService pagamentoInscricaoService){
        this.inscricaoService = inscricaoService;
        this.pagamentoInscricaoService = pagamentoInscricaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListInscricaoResponseDTO> getById(@PathVariable final long id){
        return new ResponseEntity<ListInscricaoResponseDTO>(
                ListInscricaoResponseDTO.fromEntity(inscricaoService.getById(id)),
                HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ListInscricaoResponseDTO>> list(){
        return new ResponseEntity<List<ListInscricaoResponseDTO>>(
                inscricaoService.list().stream().map(inscricao -> ListInscricaoResponseDTO.fromEntity(inscricao)).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CreateInscricaoResponseDTO> create(@Valid @RequestBody final CreateInscricaoRequestDTO createInscricaoRequestDTO){
        Inscricao inscricao = inscricaoService.create(createInscricaoRequestDTO.toEntity());
        return new ResponseEntity<CreateInscricaoResponseDTO>(
                CreateInscricaoResponseDTO.fromEntity(inscricao),
                HttpStatus.CREATED);
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<CreateInscricaoPagarResponseDTO> pay(@PathVariable final long id) throws PaymentAlreadyDoneException {
        PagamentoInscricao pagamentoInscricao = pagamentoInscricaoService.payInscricao(id);
        return new ResponseEntity<CreateInscricaoPagarResponseDTO>(
                CreateInscricaoPagarResponseDTO.fromEntity(pagamentoInscricao),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}/check")
    public ResponseEntity checkPayments(@PathVariable final long id) {
        pagamentoInscricaoService.checkPayments(id);
        return ResponseEntity.noContent().build();
    }
}
