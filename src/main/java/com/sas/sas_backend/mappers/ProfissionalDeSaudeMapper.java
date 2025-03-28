package com.sas.sas_backend.mappers;

import com.sas.sas_backend.dtos.ProfissionalDeSaudeDto;
import com.sas.sas_backend.models.ProfissionalDeSaude;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfissionalDeSaudeMapper {
    ProfissionalDeSaude toProfissionalDeSaude(ProfissionalDeSaudeDto dto);

    ProfissionalDeSaudeDto toProfissionalDeSaudeDto(ProfissionalDeSaude profissional);
}
