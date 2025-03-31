package com.sas.sas_backend.models;

import com.sas.sas_backend.models.enumerated.StatusNotificacao;
import com.sas.sas_backend.models.enumerated.TipoNotificacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {
    @Id
    @Column(name = "id_notificacao", length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idNotificacao;

    // Duvida na chave estrangeira
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    // Duvida na chave estrangeira

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoNotificacao tipoNotificacao;

    @Column(name = "mensagem", columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_envio", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataEnvio;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusNotificacao statusNotificacao = StatusNotificacao.ENVIADO;

}
