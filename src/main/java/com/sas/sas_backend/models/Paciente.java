package com.sas.sas_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sas.sas_backend.models.enumerated.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Paciente {

    @Id
    @Column(name = "id_paciente", length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPaciente;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 250)
    private String senha;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    private Genero genero;

    @Column(length = 15)
    private String telefone;

    @Column(length = 50)
    private String grauInstrucao;

    @Column(nullable = false)
    private Boolean notificacoesAtivadas;

    @OneToOne(cascade = CascadeType.ALL)
    //                  coluna da entidade                    coluna da entidade de destino
    @JoinColumn(name = "endereco_id", referencedColumnName = "id_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exame> exame = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes = new ArrayList<>();

}
