package com.sas.sas_backend.repository;

import com.sas.sas_backend.Models.ProfissionalDeSaude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfissionalDeSaudeRepository extends JpaRepository<ProfissionalDeSaude, UUID> {
    Optional<ProfissionalDeSaude> findByNome(String nome);
}
