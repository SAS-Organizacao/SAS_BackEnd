package com.sas.sas_backend.Exceptions.ProfissionalDeSaude;

import com.sas.sas_backend.Exceptions.BaseException;

public class ProfissionalDeSaudeAlreadyExistsException extends BaseException {
    public ProfissionalDeSaudeAlreadyExistsException(String detail) {
        super("409", "Profissional já existente!", detail);
    }
}
