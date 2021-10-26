package com.socios.clube.esportes.repositories;

import com.socios.clube.esportes.models.PagamentoInscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoInscricaoRepository extends JpaRepository<PagamentoInscricao, Long> {

    @Query(value = "select pi.id from pagamento_inscricao pi where MONTH(pi.create_at) = :month AND YEAR(pi.create_at) = :year", nativeQuery = true)
    Optional<List<PagamentoInscricao>> findByMonthAndYear(@Param("month") final int month, @Param("year") final int year);

    @Query(value = "select pi from PagamentoInscricao pi WHERE pi.createAt BETWEEN :startDate AND :endDate")
    Optional<List<PagamentoInscricao>> findByPeriod(@Param("startDate") final LocalDateTime startDate, @Param("endDate") final LocalDateTime endDate);
}
