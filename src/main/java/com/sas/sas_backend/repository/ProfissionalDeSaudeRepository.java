package com.sas.sas_backend.repository;

import com.sas.sas_backend.models.ProfissionalDeSaude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissionalDeSaudeRepository extends JpaRepository<ProfissionalDeSaude, String> {
    Optional<ProfissionalDeSaude> findByNome(String nome);
}
