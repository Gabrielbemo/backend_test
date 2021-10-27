package com.socios.clube.esportes.controllers.dtos.out;

import com.socios.clube.esportes.models.Socio;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSocioResponseDTO {

    private Long id;

    @NotBlank(message = "name cannot be blank.")
    @NotNull(message = "name cannot be null.")
    private String name;

    @NotBlank(message = "lastName cannot be blank.")
    @NotNull(message = "lastName cannot be null.")
    private String lastName;

    @Email
    @NotBlank(message = "email cannot be blank.")
    @NotNull(message = "email cannot be null.")
    private String email;

    public static CreateSocioResponseDTO fromEntity(final Socio socio){
        return CreateSocioResponseDTO.builder()
                .id(socio.getId())
                .name(socio.getName())
                .lastName(socio.getLastName())
                .email(socio.getEmail())
                .build();
    }
}
