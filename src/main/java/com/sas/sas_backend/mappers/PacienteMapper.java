package com.sas.sas_backend.mappers;

import com.sas.sas_backend.Dtos.PacienteDto;
import com.sas.sas_backend.Models.Paciente;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PacienteMapper {
    Paciente toPaciente(PacienteDto pacienteDto);

    PacienteDto toPacienteDto(Paciente paciente);
}
