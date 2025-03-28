package com.sas.sas_backend.models.enumerated;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TipoProfissional {
    Medico("Médico"),
    Enfermeiro("Enfermeiro"),
    Dentista("Dentista"),
    Psicologo("Psicologo"),
    Fisioterapeuta("Fisioterapeuta"),
    Nutricionista("Nutricionista"),
    TecnicoDeEnfermagem("Técnico De Enfermagem");

    private final String description;

    @Override
    @JsonValue
    public String toString() {
        return description;
    }

    public static TipoProfissional fromDescription(String description) {
        for (TipoProfissional tipoProfissional : TipoProfissional.values()) {
            if (tipoProfissional.description.equals(description)) {
                return tipoProfissional;
            }
        }
        throw new IllegalArgumentException("Error: " + description);
    }

}


