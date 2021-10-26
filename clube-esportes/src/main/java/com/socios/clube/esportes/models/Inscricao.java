package com.socios.clube.esportes.models;

import com.socios.clube.esportes.models.enums.Esporte;
import com.socios.clube.esportes.models.enums.StatusInscricao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull(message = "id cannot be null.")
    private Long id;

    @Column(name = "esporte")
    @NotNull(message = "esporte cannot be null.")
    private Esporte esporte;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_socio")
    @NotNull(message = "socio cannot be null.")
    private Socio socio;

    @OneToMany(mappedBy = "inscricao")
    private List<PagamentoInscricao> pagamentoInscricoes;

    @Column(name = "status")
    @NotNull(message = "statusInscricao cannot be null.")
    private StatusInscricao statusInscricao;

    @Column(name = "create_at")
    @NotNull(message = "createAt cannot be null.")
    private LocalDateTime createAt;
}
