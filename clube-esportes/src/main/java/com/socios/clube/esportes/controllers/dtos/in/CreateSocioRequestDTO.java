package com.socios.clube.esportes.controllers.dtos.in;

import com.socios.clube.esportes.models.Socio;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSocioRequestDTO {

    @NotBlank(message = "name cannot be blank.")
    @NotNull(message = "name cannot be null.")
    private String name;

    @NotBlank(message = "lastName cannot be blank.")
    @NotNull(message = "lastName cannot be null.")
    private String lastName;

    @NotNull(message = "birthDate cannot be null.")
    private LocalDateTime birthDate;

    @Email
    @NotBlank(message = "email cannot be blank.")
    @NotNull(message = "email cannot be null.")
    private String email;

    @Pattern(regexp = "(\\(\\d{2}\\))(\\d{4,5}\\-\\d{4})")
    @NotBlank(message = "phone cannot be blank.")
    @NotNull(message = "phone cannot be null.")
    private String phone;

    @NotBlank(message = "address cannot be blank.")
    @NotNull(message = "address cannot be null.")
    private String address;

    public Socio toEntity(){
        return Socio.builder()
                .name(name)
                .lastName(lastName)
                .birthDate(birthDate)
                .email(email)
                .phone(phone)
                .address(address)
                .build();
    }
}
