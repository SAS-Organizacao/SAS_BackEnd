package com.sas.sas_backend.service;

import com.sas.sas_backend.Dtos.PacienteDto;
import com.sas.sas_backend.Exceptions.Paciente.PacienteAlreadyExistsException;
import com.sas.sas_backend.Exceptions.Paciente.PacienteNotFoundException;
import com.sas.sas_backend.Models.Endereco;
import com.sas.sas_backend.Models.Paciente;
import com.sas.sas_backend.mappers.PacienteMapper;
import com.sas.sas_backend.repository.EnderecoRepository;
import com.sas.sas_backend.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteDto buscarPorCpf(String cpf) {

        Paciente pacienteCPF = pacienteRepository.findByCpf(cpf).orElseThrow(() -> new PacienteNotFoundException(" CPF:" + cpf));
        return pacienteMapper.toPacienteDto(pacienteCPF);

    }


    @Transactional
    public PacienteDto cadastrarPaciente(PacienteDto dto) {
        if (pacienteRepository.findByEmailOrCpf(dto.email(), dto.cpf())) {
            throw new PacienteAlreadyExistsException("Email ou CPF já cadastrado !");
        }

        Paciente paciente = pacienteMapper.toPaciente(dto);

        Endereco enderecoSaved = enderecoRepository.save(paciente.getEndereco());
        paciente.setEndereco(enderecoRepository.findById(enderecoSaved.getIdEndereco()).orElse(null));

        Paciente savedPaciente = pacienteRepository.save(paciente);
        return pacienteMapper.toPacienteDto(savedPaciente);
    }


    public List<PacienteDto> buscarTodos() {
        List<Paciente> listPaciente = pacienteRepository.findAll();
        if (listPaciente.isEmpty()) {
            throw new PacienteNotFoundException("Lista não encontrada");
        }
        return listPaciente.stream().map(pacienteMapper::toPacienteDto).toList();

    }

    public PacienteDto atualizarPaciente(String id, PacienteDto dto) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado."));
        Paciente paciente = pacienteMapper.toPaciente(dto);
        paciente.setIdPaciente(pacienteExistente.getIdPaciente());
        return pacienteMapper.toPacienteDto(pacienteRepository.save(paciente));
    }

    public void deletarPaciente(String id) {
        pacienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado."));
        pacienteRepository.deleteById(id);

    }
}
