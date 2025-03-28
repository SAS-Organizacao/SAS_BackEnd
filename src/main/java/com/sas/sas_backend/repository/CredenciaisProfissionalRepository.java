package com.sas.sas_backend.repository;

import com.sas.sas_backend.models.CredenciaisProfissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredenciaisProfissionalRepository extends JpaRepository<CredenciaisProfissional, String> {
    Optional<CredenciaisProfissional> findByEmail(String email);

    Optional<CredenciaisProfissional> findByNumeroRegistro(String numeroRegistro);

    Optional<CredenciaisProfissional> findByIdProfissional(String idProfissional);
}
