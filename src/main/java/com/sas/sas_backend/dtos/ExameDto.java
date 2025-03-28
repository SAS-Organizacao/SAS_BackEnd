package com.sas.sas_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sas.sas_backend.models.enumerated.StatusExame;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExameDto(
        @NotBlank(message = "Descrição é obrigatória!")
        String descricao,

        String tipoExame,

        @NotNull(message = "Status é obrigatório!")
        StatusExame status,


         @JsonIgnore
         PacienteDto paciente,


         @JsonIgnore
         ProfissionalDeSaudeDto profissionalDeSaude


//         AgendamentoDto agendamento
) {}