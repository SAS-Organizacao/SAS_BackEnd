package com.sas.sas_backend.exceptions.credenciaisProfissional;

import com.sas.sas_backend.exceptions.BaseException;

public class CredenciaisProfissionalNotFoundException extends BaseException {
    public CredenciaisProfissionalNotFoundException(String detail) {
        super("204", "credencial não encontrado", detail);
    }
}