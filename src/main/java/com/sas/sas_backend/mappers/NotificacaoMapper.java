package com.sas.sas_backend.mappers;

import com.sas.sas_backend.dtos.NotificacaoDto;
import com.sas.sas_backend.models.Notificacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificacaoMapper {

    Notificacao toNotificacao(NotificacaoDto notificacaoDto);
    NotificacaoDto toNotificacaoDto(Notificacao notificacao);
}
