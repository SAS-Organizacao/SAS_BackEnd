package com.sas.sas_backend.Exceptions.ProfissionalDeSaude;

import com.sas.sas_backend.Exceptions.BaseException;

public class ProfissionalDeSaudeNotFoundException extends BaseException {
    public ProfissionalDeSaudeNotFoundException(String detail) {
        super("204", "Profissional não encontrado!",detail);
    }
}
