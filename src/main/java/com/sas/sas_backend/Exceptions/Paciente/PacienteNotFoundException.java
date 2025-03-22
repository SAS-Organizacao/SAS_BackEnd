package com.sas.sas_backend.Exceptions.Paciente;

import com.sas.sas_backend.Exceptions.BaseException;

public class PacienteNotFoundException extends BaseException {

        public PacienteNotFoundException(String detail) {
            super("204","Paciente não encontrado !", detail);
        }
}
