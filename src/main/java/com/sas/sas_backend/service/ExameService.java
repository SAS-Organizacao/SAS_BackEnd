package com.sas.sas_backend.service;

import com.sas.sas_backend.dtos.ExameDto;
import com.sas.sas_backend.exceptions.exame.ExameAlreadyExistsException;
import com.sas.sas_backend.exceptions.exame.ExameNotFoundException;
import com.sas.sas_backend.mappers.ExameMapper;
import com.sas.sas_backend.models.Exame;
import com.sas.sas_backend.repository.ExameRepository;
import com.sas.sas_backend.repository.PacienteRepository;
import com.sas.sas_backend.repository.ProfissionalDeSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExameService {
    private final ProfissionalDeSaudeRepository profissionalDeSaudeRepository;
    private final PacienteRepository pacienteRepository;
    private final ExameRepository exameRepository;
    private final ExameMapper exameMapper;
    //    private final AgendamentoRepository agendamentoRepository;

    @Transactional
    public ExameDto cadastrarExame(ExameDto exameDto) {
        if (exameRepository.findByPaciente_Cpf(exameDto.paciente().cpf()).isPresent()) {
            throw new ExameAlreadyExistsException("Exame ja cadastrado");
        }
        Exame exame = exameMapper.toExame(exameDto);
        exameRepository.save(exame);
        return exameMapper.toExameDto(exame);
    }

    public List<ExameDto> listarExames() {
        List<Exame> exames = exameRepository.findAll();
        if (exames.isEmpty()) {
            throw new ExameNotFoundException("Exames não encontrados");
        }
        return exames.stream().map(exameMapper::toExameDto).collect(Collectors.toList());
    }

    public List<ExameDto> listarExamesPacienteCPF(String cpf) {
        List<Exame> exameExistente = exameRepository.listarExamesPacienteCPF(cpf);
        if (exameExistente.isEmpty()) {
            throw new ExameNotFoundException("Exame não encontrado" + cpf);
        }
        return exameExistente.stream().map(exameMapper::toExameDto).collect(Collectors.toList());
    }

    public void removerExame(String id) {
        exameRepository.findById(id).orElseThrow(() -> new ExameNotFoundException(id));
        exameRepository.deleteById(id);
    }

    public ExameDto atualizarExame(String id, ExameDto dto) {
        Exame exameExistente = exameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Exame não encontrado."));
        Exame exame = exameMapper.toExame(dto);
        exame.setIdExame(exameExistente.getIdExame());
        return exameMapper.toExameDto(exameRepository.save(exame));
    }
}