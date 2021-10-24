package com.socios.clube.esportes.services;

import com.socios.clube.esportes.controllers.exceptions.PaymentAlreadyDoneException;
import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.PagamentoInscricao;
import com.socios.clube.esportes.repositories.InscricaoRepository;
import com.socios.clube.esportes.repositories.PagamentoInscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoInscricaoService {
    private final PagamentoInscricaoRepository pagamentoInscricaoRepository;
    private final InscricaoRepository inscricaoRepository;

    @Autowired
    public PagamentoInscricaoService(final PagamentoInscricaoRepository pagamentoInscricaoRepository, final InscricaoRepository inscricaoRepository){
        this.pagamentoInscricaoRepository = pagamentoInscricaoRepository;
        this.inscricaoRepository = inscricaoRepository;
    }

    public PagamentoInscricao payInscricao(final Long idInscricao) throws PaymentAlreadyDoneException {
        Optional<Inscricao> inscricao = inscricaoRepository.findById(idInscricao);
        if (inscricao.isEmpty())
            throw new EntityNotFoundException(String.format("Unable to find com.socios.clube.esportes.models.Inscricao with id %s", idInscricao));

        int month = LocalDateTime.now().getMonth().getValue();
        int year = LocalDateTime.now().getYear();

        Optional<List<PagamentoInscricao>> pagamentoInscricoes = pagamentoInscricaoRepository.findByMonthAndYear(month, year);
        if(pagamentoInscricoes.get().isEmpty()){
            PagamentoInscricao newPagamentoInscricao = PagamentoInscricao.builder()
                            .inscricao(inscricao.get())
                            .createAt(LocalDateTime.now())
                            .build();
            pagamentoInscricaoRepository.save(newPagamentoInscricao);
            return pagamentoInscricaoRepository.getById(newPagamentoInscricao.getId());
        }else {
            throw new PaymentAlreadyDoneException("Payment already done on this date");
        }
    }
}
