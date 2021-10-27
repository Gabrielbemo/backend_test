package com.socios.clube.esportes.services;

import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.enums.StatusInscricao;
import com.socios.clube.esportes.repositories.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final PagamentoInscricaoService pagamentoInscricaoService;

    @Autowired
    public InscricaoService(final InscricaoRepository inscricaoRepository, final PagamentoInscricaoService pagamentoInscricaoService){
        this.inscricaoRepository = inscricaoRepository;
        this.pagamentoInscricaoService = pagamentoInscricaoService;
    }

    public Inscricao getById(Long id){
        pagamentoInscricaoService.checkPayments(id);
        return inscricaoRepository.getById(id);
    }

    public List<Inscricao> list(){
        return inscricaoRepository.findAll().stream().map(inscricao -> {
            pagamentoInscricaoService.checkPayments(inscricao.getId());
            return inscricao;
        }).collect(Collectors.toList());
    }

    public Inscricao create(final Inscricao inscricao){
        inscricao.setStatusInscricao(StatusInscricao.DESATIVADA);
        inscricao.setCreateAt(LocalDateTime.now());
        inscricaoRepository.save(inscricao);
        return inscricaoRepository.getById(inscricao.getId());
    }
}
