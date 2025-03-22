package com.sas.sas_backend.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record ProfissionalDeSaudeDto(
        UUID idProfissionalDeSaude,

        @NotBlank(message = "Nome é obrigatório!")
        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+${1,50}", message = "Não sei o que colocar!!!!!!")
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone
) {}