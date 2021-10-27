package com.socios.clube.esportes.controllers.dtos.out;

import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.PagamentoInscricao;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateInscricaoPagarResponseDTO {

    private Long id;

    @NotNull(message = "inscricao cannot be null.")
    private Long inscricaoId;

    @NotNull(message = "inscricao cannot be null.")
    private LocalDateTime createAt;

    public static CreateInscricaoPagarResponseDTO fromEntity(final PagamentoInscricao pagamentoInscricao){
        return CreateInscricaoPagarResponseDTO.builder()
                .id(pagamentoInscricao.getId())
                .inscricaoId(Inscricao.builder().id(pagamentoInscricao.getInscricao().getId()).build().getId())
                .createAt(pagamentoInscricao.getCreateAt())
                .build();
    }
}
