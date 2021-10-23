package com.socios.clube.esportes.services;

import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.enums.StatusInscricao;
import com.socios.clube.esportes.repositories.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;

    @Autowired
    public InscricaoService(final InscricaoRepository inscricaoRepository){
        this.inscricaoRepository = inscricaoRepository;
    }

    public Inscricao create(final Inscricao inscricao){
        inscricao.setStatusInscricao(StatusInscricao.DESATIVADA);
        inscricao.setCreateAt(LocalDateTime.now());
        inscricaoRepository.save(inscricao);
        return inscricaoRepository.getById(inscricao.getId());
    }
}
