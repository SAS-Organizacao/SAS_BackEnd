package com.sas.sas_backend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPaciente;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 50)
    private String nome;

    @Email
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

    public Paciente(String cpf, String nome, String email, String senha, LocalDate dataNascimento, Genero genero, String telefone, String grauInstrucao, Boolean notificacoesAtivadas) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.telefone = telefone;
        this.grauInstrucao = grauInstrucao;
        this.notificacoesAtivadas = notificacoesAtivadas;
    }
}
