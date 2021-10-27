package com.socios.clube.esportes.services;

import com.socios.clube.esportes.controllers.exceptions.PaymentAlreadyDoneException;
import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.PagamentoInscricao;
import com.socios.clube.esportes.models.enums.StatusInscricao;
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

        Optional<List<PagamentoInscricao>> pagamentoInscricoes = pagamentoInscricaoRepository.findByMonthAndYear(inscricao.get().getId(), month, year);
        if(pagamentoInscricoes.get().isEmpty()){
            PagamentoInscricao newPagamentoInscricao = PagamentoInscricao.builder()
                            .inscricao(inscricao.get())
                            .createAt(LocalDateTime.now())
                            .build();
            pagamentoInscricaoRepository.save(newPagamentoInscricao);
            inscricao.get().setStatusInscricao(StatusInscricao.ATIVA);
            inscricaoRepository.save(inscricao.get());
            return pagamentoInscricaoRepository.getById(newPagamentoInscricao.getId());
        }else {
            throw new PaymentAlreadyDoneException("Payment already done on this date");
        }
    }

    public PagamentoInscricao findCurrentPaymentPaid(Inscricao inscricao, List<PagamentoInscricao> pagamentoInscricoes){
        PagamentoInscricao actualPayment = null;
        int actualDay = LocalDateTime.now().getDayOfMonth();
        if(actualDay < inscricao.getCreateAt().getDayOfMonth()){
            actualPayment = pagamentoInscricoes.stream().filter(pagamentoInscricao -> ((pagamentoInscricao.getCreateAt().getMonth() == LocalDateTime.now().getMonth() &&
                            pagamentoInscricao.getCreateAt().getDayOfMonth() < inscricao.getCreateAt().getDayOfMonth()) ||
                            (pagamentoInscricao.getCreateAt().getMonth() == LocalDateTime.now().getMonth().minus(1) &&
                                    pagamentoInscricao.getCreateAt().getDayOfMonth() > inscricao.getCreateAt().getDayOfMonth())))
                    .findAny()
                    .orElse(null);
        }
        if(actualDay > inscricao.getCreateAt().getDayOfMonth()){
            actualPayment = pagamentoInscricoes.stream().filter(pagamentoInscricao -> ((pagamentoInscricao.getCreateAt().getMonth() == LocalDateTime.now().getMonth() &&
                            pagamentoInscricao.getCreateAt().getDayOfMonth() > inscricao.getCreateAt().getDayOfMonth()) ||
                            (pagamentoInscricao.getCreateAt().getMonth() == LocalDateTime.now().getMonth().plus(1) &&
                                    pagamentoInscricao.getCreateAt().getDayOfMonth() < inscricao.getCreateAt().getDayOfMonth())))
                    .findAny()
                    .orElse(null);
        }
        return actualPayment;
    }

    public PagamentoInscricao findLastPaymentPaid(Inscricao inscricao, List<PagamentoInscricao> pagamentoInscricoes){
        PagamentoInscricao lastPayment = null;
        int actualDay = LocalDateTime.now().getDayOfMonth();
        if(actualDay >= inscricao.getCreateAt().getDayOfMonth()){
            lastPayment = pagamentoInscricoes.stream().filter(pagamentoInscricao -> ((pagamentoInscricao.getCreateAt().getMonth() == LocalDateTime.now().getMonth() &&
                            pagamentoInscricao.getCreateAt().getYear() == LocalDateTime.now().getYear() &&
                            pagamentoInscricao.getCreateAt().getDayOfMonth() < inscricao.getCreateAt().getDayOfMonth()) ||
                            (pagamentoInscricao.getCreateAt().getMonth() == LocalDateTime.now().getMonth().minus(1) &&
                                    (pagamentoInscricao.getCreateAt().getYear() == LocalDateTime.now().getYear() ||
                                            pagamentoInscricao.getCreateAt().getYear() == LocalDateTime.now().getYear()-1) &&
                                    pagamentoInscricao.getCreateAt().getDayOfMonth() >= inscricao.getCreateAt().getDayOfMonth())))
                    .findAny()
                    .orElse(null);
        }else if(actualDay < inscricao.getCreateAt().getDayOfMonth()){
            lastPayment = pagamentoInscricoes.stream().filter(pagamentoInscricao -> ((pagamentoInscricao.getCreateAt().getMonth() == LocalDateTime.now().getMonth().minus(1) &&
                            (pagamentoInscricao.getCreateAt().getYear() == LocalDateTime.now().getYear() ||
                                    pagamentoInscricao.getCreateAt().getYear() == LocalDateTime.now().getYear()-1) &&
                            pagamentoInscricao.getCreateAt().getDayOfMonth() < inscricao.getCreateAt().getDayOfMonth()) ||
                            (pagamentoInscricao.getCreateAt().getMonth() == LocalDateTime.now().getMonth().minus(2) &&
                                    (pagamentoInscricao.getCreateAt().getYear() == LocalDateTime.now().getYear() ||
                                            pagamentoInscricao.getCreateAt().getYear() == LocalDateTime.now().getYear()-1) &&
                                    pagamentoInscricao.getCreateAt().getDayOfMonth() >= inscricao.getCreateAt().getDayOfMonth())))
                    .findAny()
                    .orElse(null);
        }
        return lastPayment;
    }

    public void checkPayments(final Long idInscricao){
        Optional<Inscricao> inscricao = inscricaoRepository.findById(idInscricao);
        if(inscricao.isPresent()) {
            Optional<List<PagamentoInscricao>> pagamentoInscricoes = pagamentoInscricaoRepository.findByPeriod(inscricao.get().getCreateAt(), LocalDateTime.now());

            PagamentoInscricao actualPayment = findCurrentPaymentPaid(inscricao.get(), pagamentoInscricoes.get());

            if(actualPayment != null){
                if(inscricao.get().getStatusInscricao() == StatusInscricao.DESATIVADA){
                    inscricao.get().setStatusInscricao(StatusInscricao.ATIVA);
                    inscricaoRepository.save(inscricao.get());
                }
            }else {
                PagamentoInscricao lastPayment = findLastPaymentPaid(inscricao.get(), pagamentoInscricoes.get());
                if(lastPayment == null){
                    if(inscricao.get().getStatusInscricao() == StatusInscricao.ATIVA){
                        inscricao.get().setStatusInscricao(StatusInscricao.DESATIVADA);
                        inscricaoRepository.save(inscricao.get());
                    }
                }
            }
        }
    }
}
