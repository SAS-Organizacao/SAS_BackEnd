package com.sas.sas_backend.Exceptions.Paciente;

import com.sas.sas_backend.Exceptions.BaseException;

public class PacienteAlreadyExistsException extends BaseException {

    public PacienteAlreadyExistsException(String detail) {
        super("409","Paciente ja existente !", detail);
    }
}
