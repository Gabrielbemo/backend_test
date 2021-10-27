package com.socios.clube.esportes.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Socio {

    public Socio(Long id){
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "id cannot be null.")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "name cannot be blank.")
    @NotNull(message = "name cannot be null.")
    private String name;

    @Column(name = "last_name")
    @NotBlank(message = "lastName cannot be blank.")
    @NotNull(message = "lastName cannot be null.")
    private String lastName;

    @Column(name = "birth_date")
    @NotNull(message = "birthDate cannot be null.")
    private LocalDateTime birthDate;

    @Column(name = "email")
    @Email
    @NotBlank(message = "email cannot be blank.")
    @NotNull(message = "email cannot be null.")
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "(\\(\\d{2}\\))(\\d{4,5}\\-\\d{4})")
    @NotBlank(message = "phone cannot be blank.")
    @NotNull(message = "phone cannot be null.")
    private String phone;

    @Column(name = "address")
    @NotBlank(message = "address cannot be blank.")
    @NotNull(message = "address cannot be null.")
    private String address;

    @Override
    public boolean equals(final Object object){
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        Socio socio = (Socio) object;

        return this.id == socio.getId() &&
                this.name == socio.getName() &&
                this.lastName == socio.getName() &&
                this.email == socio.getName() &&
                this.birthDate.equals(socio.getName()) &&
                this.phone == socio.getName() &&
                this.address == socio.getName();
    }
}
