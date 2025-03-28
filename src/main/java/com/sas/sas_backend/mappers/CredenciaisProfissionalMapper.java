package com.sas.sas_backend.mappers;

import com.sas.sas_backend.dtos.CredenciaisProfissionalDto;
import com.sas.sas_backend.models.CredenciaisProfissional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredenciaisProfissionalMapper {
    CredenciaisProfissional tocredenciaisProfissional(CredenciaisProfissionalDto credenciaisProfissionalDto);
    CredenciaisProfissionalDto toCredenciaisProfissionalDto(CredenciaisProfissional credenciaisProfissional);
}
