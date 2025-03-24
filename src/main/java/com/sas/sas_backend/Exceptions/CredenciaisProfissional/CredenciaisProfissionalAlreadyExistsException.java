package com.sas.sas_backend.Exceptions.CredenciaisProfissional;

import com.sas.sas_backend.Exceptions.BaseException;

public class CredenciaisProfissionalAlreadyExistsException extends BaseException {
    public CredenciaisProfissionalAlreadyExistsException(String detail) {
        super("409", "Credencial Já existe no sistema de profissional", detail);
    }
}

