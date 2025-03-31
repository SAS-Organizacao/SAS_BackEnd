

package com.sas.sas_backend.models.enumerated;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StatusNotificacao {
    ENVIADO("Enviado"),
    LIDO("Lido"),
    CANCELADO("Cancelado");

    private final String description;

    @Override
    @JsonValue
    public String toString() {return description;}
}
