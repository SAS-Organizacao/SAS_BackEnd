package com.sas.sas_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profissional_de_saude")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalDeSaude {

    @Id
    @Column(name = "id_profissional_de_saude", length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idProfissionalDeSaude;

    @Column(name = "unidade_de_saude_id", length = 36)
    private String idUnidadeSaude;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(length = 15)
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exame> exame = new ArrayList<>();



}
