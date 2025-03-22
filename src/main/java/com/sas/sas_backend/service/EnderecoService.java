package com.sas.sas_backend.service;
import com.sas.sas_backend.Dtos.EnderecoDto;
import com.sas.sas_backend.Models.Endereco;
import com.sas.sas_backend.mappers.EnderecoMapper;
import com.sas.sas_backend.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;

    public EnderecoDto cadastrarEndereco(EnderecoDto dto) {

        Endereco endereco = enderecoMapper.toEndereco(dto);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoMapper.toEnderecoDto(enderecoSalvo);
    }

    public EnderecoDto atualizarEndereco(UUID id, EnderecoDto dto) {
        Endereco enderecoExistente = enderecoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado."));
        Endereco endereco = enderecoMapper.toEndereco(dto);
        endereco.setIdEndereco(enderecoExistente.getIdEndereco());
        return enderecoMapper.toEnderecoDto(enderecoRepository.save(endereco));
    }

    public void deletarEndereco(UUID id) {
        enderecoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado."));
        enderecoRepository.deleteById(id);

    }


}
