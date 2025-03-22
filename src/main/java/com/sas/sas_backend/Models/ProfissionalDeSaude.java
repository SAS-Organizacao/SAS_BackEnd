package com.sas.sas_backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "profissional_de_saude")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalDeSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idProfissionalDeSaude;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(length = 15)
    private String telefone;
}
