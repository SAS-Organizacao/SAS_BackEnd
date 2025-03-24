package com.sas.sas_backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credenciais_profissional")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisProfissional {

    @Id
    @Column(name = "id_credencial", length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idCredencial;

    @Column(name = "profissional_id", length = 36)
    private String idProfissional;

    @Column(name = "unidade_de_saude_id", length = 36)
    private String idUnidadeSaude;


    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_profissional", nullable = false)
    private TipoProfissional tipoProfissional;

    @Column(nullable = false, length = 20)
    private String numeroRegistro;


}
