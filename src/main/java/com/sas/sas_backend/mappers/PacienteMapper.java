package com.sas.sas_backend.mappers;

import com.sas.sas_backend.Dtos.PacienteDto;
import com.sas.sas_backend.Models.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = EnderecoMapper.class)
public interface PacienteMapper {

    @Mapping(source = "endereco", target = "endereco")
    Paciente toPaciente(PacienteDto pacienteDto);

    PacienteDto toPacienteDto(Paciente paciente);
}
