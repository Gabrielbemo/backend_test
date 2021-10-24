package com.socios.clube.esportes.repositories;

import com.socios.clube.esportes.models.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

}
