package com.sas.sas_backend.models;

import com.sas.sas_backend.models.enumerated.StatusExame;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exame")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exame {

    @Id
    @Column(name = "id_exame", length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idExame;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "tipo_exame", length = 50)
    private String tipoExame;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusExame status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    private com.sas.sas_backend.models.Paciente paciente;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id")
    private ProfissionalDeSaude profissionalDeSaude;


//    @ManyToOne
//    @JoinColumn(name = "agendamento_id", nullable = false)
//    private Agendamento agendamento;

}
