package com.socios.clube.esportes.controllers.dtos.out;

import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.enums.Esporte;
import com.socios.clube.esportes.models.enums.StatusInscricao;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListInscricaoResponseDTO {

    private Long id;

    private Long socioId;

    @NotNull
    private Esporte esporte;

    @NotNull
    private StatusInscricao statusInscricao;

    @NotNull
    private LocalDateTime createAt;

    public static ListInscricaoResponseDTO fromEntity(final Inscricao inscricao){
        return ListInscricaoResponseDTO.builder()
                .id(inscricao.getId())
                .socioId(inscricao.getSocio().getId())
                .esporte(inscricao.getEsporte())
                .statusInscricao(inscricao.getStatusInscricao())
                .createAt(inscricao.getCreateAt())
                .build();
    }
}
