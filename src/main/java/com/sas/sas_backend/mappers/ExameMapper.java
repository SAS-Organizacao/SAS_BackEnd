package com.sas.sas_backend.mappers;

import com.sas.sas_backend.dtos.ExameDto;
import com.sas.sas_backend.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = {PacienteMapper.class, ProfissionalDeSaudeMapper.class})
public interface ExameMapper {

    @Mapping(source = "paciente", target = "paciente")
    @Mapping(source = "profissionalDeSaude", target = "profissionalDeSaude")
//    @Mapping(source = "unidadeDeSaude", target = "unidadeDeSaude")
    Exame toExame(ExameDto exameDto);

    ExameDto toExameDto(Exame exame);

    List<Exame> toListExame(List<ExameDto> exameDtoList);
    List<ExameDto> toListExameDto(List<Exame> exameList);

}
