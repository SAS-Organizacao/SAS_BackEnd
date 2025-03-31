package com.sas.sas_backend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sas.sas_backend.models.enumerated.StatusNotificacao;
import com.sas.sas_backend.models.enumerated.TipoNotificacao;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record NotificacaoDto(

        @NotNull(message = "Tipo de notificação é obrigatório!")
        TipoNotificacao tipoNotificacao,

        @NotBlank(message = "Mensagem é obrigatória!") // Mensagem pode ser null consultar os meninos???
        @Size(max = 255, message = "A mensagem não pode ter mais de 255 caracteres.")
        String mensagem,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dateTime,

        // Por padão já é "enviado"
        StatusNotificacao statusNotificacao,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime criadoEm,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime atualizadoEm

) {
}
