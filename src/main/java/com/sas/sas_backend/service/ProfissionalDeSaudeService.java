package com.sas.sas_backend.Service;

import com.sas.sas_backend.Dtos.PacienteDto;
import com.sas.sas_backend.Dtos.ProfissionalDeSaudeDto;
import com.sas.sas_backend.Exceptions.ProfissionalDeSaude.ProfissionalDeSaudeAlreadyExistsException;
import com.sas.sas_backend.Exceptions.ProfissionalDeSaude.ProfissionalDeSaudeNotFoundException;
import com.sas.sas_backend.Models.Paciente;
import com.sas.sas_backend.Models.ProfissionalDeSaude;
import com.sas.sas_backend.Repository.PacienteRepository;
import com.sas.sas_backend.Repository.ProfissionalDeSaudeRepository;
import com.sas.sas_backend.mappers.PacienteMapper;
import com.sas.sas_backend.mappers.ProfissionalDeSaudeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissionalDeSaudeService {


    private final ProfissionalDeSaudeRepository profisisonalDeSaudeRepository;
    private final ProfissionalDeSaudeMapper profissionalDeSaudeMapper;

    public ProfissionalDeSaudeDto buscarPorNome(String nome) {
        ProfissionalDeSaude profissional = profisisonalDeSaudeRepository.findByNome(nome)
                .orElseThrow(() -> new ProfissionalDeSaudeNotFoundException("Profissional não encontrado com o nome: " + nome));
        return profissionalDeSaudeMapper.toProfissionalDeSaudeDto(profissional);
    }

    public ProfissionalDeSaudeDto cadastrarProfissionalDeSaude(ProfissionalDeSaudeDto dto) {
        profisisonalDeSaudeRepository.findByNome(dto.nome()).ifPresent(profissional -> {
            throw new ProfissionalDeSaudeAlreadyExistsException("Profissional já cadastrado com o nome: " + dto.nome());
        });

        ProfissionalDeSaude profissional = profissionalDeSaudeMapper.toProfissionalDeSaude(dto);
        return profissionalDeSaudeMapper.toProfissionalDeSaudeDto(profisisonalDeSaudeRepository.save(profissional));
    }

    public List<ProfissionalDeSaudeDto> buscarTodos() {
        List<ProfissionalDeSaude> profissionais = profisisonalDeSaudeRepository.findAll();
        if (profissionais.isEmpty()) {
            throw new ProfissionalDeSaudeNotFoundException("Nenhum profissional encontrado.");
        }
        return profissionais.stream().map(profissionalDeSaudeMapper::toProfissionalDeSaudeDto).collect(Collectors.toList());
    }

    public ProfissionalDeSaudeDto atualizarProfissionalDeSaude(UUID id, ProfissionalDeSaudeDto dto) {
        ProfissionalDeSaude profissionalExistente = profisisonalDeSaudeRepository.findById(id)
                .orElseThrow(() -> new ProfissionalDeSaudeNotFoundException("Profissional não encontrado com o ID: " + id));
        ProfissionalDeSaude profissional = profissionalDeSaudeMapper.toProfissionalDeSaude(dto);
        profissional.setIdProfissionalDeSaude(profissionalExistente.getIdProfissionalDeSaude());
        return profissionalDeSaudeMapper.toProfissionalDeSaudeDto(profisisonalDeSaudeRepository.save(profissional));
    }

    public void deletarProfissionalDeSaude(UUID id) {
        profisisonalDeSaudeRepository.findById(id).orElseThrow(() -> new ProfissionalDeSaudeNotFoundException("Profissional não encontrado com o ID: " + id));
        profisisonalDeSaudeRepository.deleteById(id);
    }


}