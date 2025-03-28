package com.sas.sas_backend.service;

import com.sas.sas_backend.dtos.ProfissionalDeSaudeDto;
import com.sas.sas_backend.exceptions.profissionalDeSaude.ProfissionalDeSaudeAlreadyExistsException;
import com.sas.sas_backend.exceptions.profissionalDeSaude.ProfissionalDeSaudeNotFoundException;
import com.sas.sas_backend.models.ProfissionalDeSaude;
import com.sas.sas_backend.mappers.ProfissionalDeSaudeMapper;
import com.sas.sas_backend.repository.ProfissionalDeSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return profissionais.stream().map(profissionalDeSaudeMapper::toProfissionalDeSaudeDto).toList();
    }

    public ProfissionalDeSaudeDto atualizarProfissionalDeSaude(String id, ProfissionalDeSaudeDto dto) {
        ProfissionalDeSaude profissionalExistente = profisisonalDeSaudeRepository.findById(id)
                .orElseThrow(() -> new ProfissionalDeSaudeNotFoundException("Profissional não encontrado com o ID: " + id));
        ProfissionalDeSaude profissional = profissionalDeSaudeMapper.toProfissionalDeSaude(dto);
        profissional.setIdProfissionalDeSaude(profissionalExistente.getIdProfissionalDeSaude());
        return profissionalDeSaudeMapper.toProfissionalDeSaudeDto(profisisonalDeSaudeRepository.save(profissional));
    }

    public void deletarProfissionalDeSaude(String id) {
        profisisonalDeSaudeRepository.findById(id).orElseThrow(() -> new ProfissionalDeSaudeNotFoundException("Profissional não encontrado com o ID: " + id));
        profisisonalDeSaudeRepository.deleteById(id);
    }


}