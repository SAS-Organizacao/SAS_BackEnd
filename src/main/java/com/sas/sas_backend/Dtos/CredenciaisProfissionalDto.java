package com.sas.sas_backend.Dtos;

import com.sas.sas_backend.Models.TipoProfissional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CredenciaisProfissionalDto(

        @NotBlank(message = "Email é obrigatório!")
        @Email(message = "Deve ser um endereço de e-mail válido.")
        String email,

        @NotBlank(message = "Senha é obrigatória!")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "A senha deve conter pelo menos 8 caracteres, uma letra, um número e um caractere especial.")
        String senha,

        @NotNull(message = "Tipo do profissional é obrigatório!")
        TipoProfissional tipoProfissional,


        @NotBlank(message = "Numero de Registro é obrigatório!")
        String numeroRegistro,

        String idProfissional,

        String idUnidadeSaude
) {
}







