package com.socios.clube.esportes.controllers.dtos.in;

import com.socios.clube.esportes.models.Inscricao;
import com.socios.clube.esportes.models.Socio;
import com.socios.clube.esportes.models.enums.Esporte;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateInscricaoRequestDTO {

    @NotNull
    private Esporte esporte;

    @NotNull
    private Socio socio;

    public Inscricao toEntity(){
        return Inscricao.builder()
                .esporte(esporte)
                .socio(socio)
                .build();
    }
}
