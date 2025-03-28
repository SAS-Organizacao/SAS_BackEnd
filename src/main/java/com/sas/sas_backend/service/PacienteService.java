package com.sas.sas_backend.service;

import com.sas.sas_backend.dtos.ExameDto;
import com.sas.sas_backend.dtos.PacienteDto;
import com.sas.sas_backend.exceptions.paciente.PacienteAlreadyExistsException;
import com.sas.sas_backend.exceptions.paciente.PacienteNotFoundException;
import com.sas.sas_backend.mappers.ExameMapper;
import com.sas.sas_backend.models.Endereco;
import com.sas.sas_backend.models.Exame;
import com.sas.sas_backend.models.Paciente;
import com.sas.sas_backend.mappers.PacienteMapper;
import com.sas.sas_backend.repository.EnderecoRepository;
import com.sas.sas_backend.repository.ExameRepository;
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
    private final ExameRepository exameRepository;
    private final ExameMapper exameMapper;

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

    public PacienteDto adicionarExame(String id, ExameDto dto) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado."));
        Exame exame = exameMapper.toExame(dto);
        exame.setPaciente(paciente);

        Exame exameSalvo = exameRepository.save(exame);
        return pacienteMapper.toPacienteDto(pacienteRepository.save(paciente));


    }
}
