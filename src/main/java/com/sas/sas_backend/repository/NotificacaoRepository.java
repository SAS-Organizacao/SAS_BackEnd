package com.sas.sas_backend.repository;

import com.sas.sas_backend.models.Notificacao;
import com.sas.sas_backend.models.enumerated.StatusNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, String> {
    List<Notificacao> findByStatusNotificacao(StatusNotificacao status);
    // Quais são os outros tipos de buscas que vamos fazer na parte da notificação
}
