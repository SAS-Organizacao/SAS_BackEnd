package com.sas.sas_backend.repository;


import com.sas.sas_backend.models.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ExameRepository extends JpaRepository<Exame, String> {

    //    @Query("SELECT e FROM Exame e JOIN e.paciente p WHERE p.cpf = :cpf")
    Optional<Exame> findByPaciente_Cpf(@Param("cpf") String cpf);

    @Query("SELECT e FROM Exame e WHERE e.paciente.cpf = :cpf")
    List<Exame> listarExamesPacienteCPF(@Param("cpf") String cpf);

}