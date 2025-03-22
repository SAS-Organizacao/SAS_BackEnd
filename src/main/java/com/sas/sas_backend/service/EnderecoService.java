package com.sas.sas_backend.Service;

import com.sas.sas_backend.Dtos.EnderecoDto;
import com.sas.sas_backend.Models.Endereco;
import com.sas.sas_backend.Repository.EnderecoRepository;
import com.sas.sas_backend.Repository.PacienteRepository;
import com.sas.sas_backend.mappers.EnderecoMapper;
import com.sas.sas_backend.mappers.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;
    private final EnderecoMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;

    public EnderecoDto cadastrarEndereco(EnderecoDto dto){

        Endereco endereco = enderecoMapper.toEndereco(dto);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoMapper.toEnderecoDto(enderecoSalvo);
    }
    public List<EnderecoDto> buscarEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(enderecoMapper::toEnderecoDto).collect(Collectors.toList());
    }
    public EnderecoDto atualizarEndereco(UUID id, EnderecoDto dto) {

    }

}
