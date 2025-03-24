package com.sas.sas_backend.Exceptions.CredenciaisProfissional;

import com.sas.sas_backend.Exceptions.BaseException;

public class CredenciaisProfissionalNotFoundException extends BaseException {
    public CredenciaisProfissionalNotFoundException(String detail) {
        super("204", "credencial não encontrado", detail);
    }
}