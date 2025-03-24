package com.sas.sas_backend.mappers;

import com.sas.sas_backend.Dtos.CredenciaisProfissionalDto;
import com.sas.sas_backend.Models.CredenciaisProfissional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredenciaisProfissionalMapper {
    CredenciaisProfissional tocredenciaisProfissional(CredenciaisProfissionalDto credenciaisProfissionalDto);
    CredenciaisProfissionalDto toCredenciaisProfissionalDto(CredenciaisProfissional credenciaisProfissional);
}
