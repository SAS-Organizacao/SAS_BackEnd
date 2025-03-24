package com.sas.sas_backend.mappers;

import com.sas.sas_backend.Dtos.EnderecoDto;
import com.sas.sas_backend.Models.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEndereco(EnderecoDto enderecoDto);

    EnderecoDto toEnderecoDto(Endereco endereco);
}
