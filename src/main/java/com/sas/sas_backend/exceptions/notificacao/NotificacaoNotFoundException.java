package com.sas.sas_backend.exceptions.notificacao;

import com.sas.sas_backend.exceptions.BaseException;

public class NotificacaoNotFoundException extends BaseException {
    public NotificacaoNotFoundException(String detail) {
        super("204", "Notifição não encontrada!", detail);
    }
}
