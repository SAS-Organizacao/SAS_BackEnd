package com.sas.sas_backend.exceptions.notificacao;

import com.sas.sas_backend.exceptions.BaseException;

public class NotificacaoAlreadyExistsException extends BaseException {
    public NotificacaoAlreadyExistsException(String detail) {
        super("409", "Notificacao já existente!", detail);
    }
    // Realmente precisa de um "Notificacao ja existente"?
}
