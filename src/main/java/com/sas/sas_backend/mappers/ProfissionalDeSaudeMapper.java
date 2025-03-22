package com.sas.sas_backend.mappers;

import com.sas.sas_backend.Dtos.ProfissionalDeSaudeDto;
import com.sas.sas_backend.Models.ProfissionalDeSaude;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfissionalDeSaudeMapper {
    ProfissionalDeSaude toProfissionalDeSaude(ProfissionalDeSaudeDto dto);

    ProfissionalDeSaudeDto toProfissionalDeSaudeDto(ProfissionalDeSaude profissional);
}
