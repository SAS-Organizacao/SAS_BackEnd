package com.sas.sas_backend.service;

import com.sas.sas_backend.Dtos.PacienteDto;
import com.sas.sas_backend.Exceptions.Paciente.PacienteAlreadyExistsException;
import com.sas.sas_backend.Exceptions.Paciente.PacienteNotFoundException;
import com.sas.sas_backend.Models.Paciente;
import com.sas.sas_backend.mappers.PacienteMapper;
import com.sas.sas_backend.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {


    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteDto buscarPorCpf(String cpf) {

        Paciente pacienteCPF = pacienteRepository.findByCpf(cpf).orElseThrow(() -> new PacienteNotFoundException(" CPF:" + cpf));
        return pacienteMapper.toPacienteDto(pacienteCPF);

    }


    public PacienteDto cadastrarPaciente(PacienteDto dto) {
        pacienteRepository.findByCpf(dto.cpf()).ifPresent(paciente -> {
            throw new PacienteAlreadyExistsException("CPF ja cadastrado !");
        });
        pacienteRepository.findByEmail(dto.email()).ifPresent(paciente -> {
            throw new PacienteAlreadyExistsException("Email já cadastrado!");
        });

        Paciente paciente = pacienteMapper.toPaciente(dto);

        return pacienteMapper.toPacienteDto(pacienteRepository.save(paciente));
    }

    public List<PacienteDto> buscarTodos() {
        List<Paciente> listPaciente = pacienteRepository.findAll();
        if (listPaciente.isEmpty()) {
            throw new PacienteNotFoundException("Lista não encontrada");
        }
        return listPaciente.stream().map(pacienteMapper::toPacienteDto).collect(Collectors.toList());

    }

    public PacienteDto atualizarPaciente(UUID id, PacienteDto dto) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado."));
        Paciente paciente = pacienteMapper.toPaciente(dto);
        paciente.setIdPaciente(pacienteExistente.getIdPaciente());
        return pacienteMapper.toPacienteDto(pacienteRepository.save(paciente));
    }

    public void deletarPaciente(UUID id) {
        pacienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado."));
        pacienteRepository.deleteById(id);

    }
}
