package com.socios.clube.esportes.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoInscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull(message = "id cannot be null.")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_inscricao")
    @NotNull(message = "inscricao cannot be null.")
    private Inscricao inscricao;

    @Column(name = "create_at")
    @NotNull(message = "createAt cannot be null.")
    private LocalDateTime createAt;

}
