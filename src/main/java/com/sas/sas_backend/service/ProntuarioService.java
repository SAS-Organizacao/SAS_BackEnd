package com.sas.sas_backend.service;

import com.sas.sas_backend.dtos.ProntuarioDto;
import com.sas.sas_backend.dtos.response.ProntuarioResponse;
import com.sas.sas_backend.exceptions.paciente.PacienteNotFoundException;
import com.sas.sas_backend.exceptions.profissionalDeSaude.ProfissionalDeSaudeNotFoundException;
import com.sas.sas_backend.exceptions.prontuario.DuplicateRegistrationException;
import com.sas.sas_backend.mappers.ProntuarioMapper;
import com.sas.sas_backend.models.Paciente;
import com.sas.sas_backend.models.ProfissionalDeSaude;
import com.sas.sas_backend.models.Prontuario;
import com.sas.sas_backend.repository.PacienteRepository;
import com.sas.sas_backend.repository.ProfissionalDeSaudeRepository;
import com.sas.sas_backend.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalDeSaudeRepository profissionalDeSaudeRepository;
    private final ProntuarioMapper prontuarioMapper;

    @Transactional
    public ProntuarioResponse create(ProntuarioDto prontuarioDto) {

        if (prontuarioDto.pacienteCpf() == null || prontuarioDto.profissionalNumero() == null) {
            throw new IllegalArgumentException("CPF do paciente e número do profissional são obrigatórios.");
        }

        prontuarioRepository.findByPaciente_Cpf(prontuarioDto.pacienteCpf())
                .ifPresent(p -> {
                    throw new DuplicateRegistrationException("Paciente já possui prontuário cadastrado.");
                });

        Paciente paciente = pacienteRepository.findByCpf(prontuarioDto.pacienteCpf()).orElseThrow(() ->
                new PacienteNotFoundException("Paciente com o CPF informado não foi encontrado."));

        ProfissionalDeSaude profissionalDeSaude = profissionalDeSaudeRepository.findByNumeroRegistro
                        (prontuarioDto.profissionalNumero())
                .orElseThrow(() -> new ProfissionalDeSaudeNotFoundException("Profissional de saúde com o número de registro informado não foi encontrado."));

        Prontuario prontuario = prontuarioMapper.toEntity(prontuarioDto);
        prontuario.setPaciente(paciente);
        prontuario.setProfissionalDeSaude(profissionalDeSaude);
        Prontuario savedProntuario = prontuarioRepository.save(prontuario);
        return prontuarioMapper.toResponse(savedProntuario);

    }

    public ProntuarioResponse buscarPorCpf(String cpf) {
        Prontuario prontuario = prontuarioRepository.findByPaciente_Cpf(cpf)
                .orElseThrow(() -> new PacienteNotFoundException("Prontuário não encontrado para o CPF informado."));
        return prontuarioMapper.toResponse(prontuario);

    }
    @Transactional
    public ProntuarioResponse atualizar(String cpf, ProntuarioDto prontuarioDto) {

        Prontuario prontuario = prontuarioRepository.findByPaciente_Cpf(cpf)
                .orElseThrow(() -> new PacienteNotFoundException("Prontuário não encontrado para o CPF informado."));

        ProfissionalDeSaude profissional = profissionalDeSaudeRepository.findByNumeroRegistro(prontuarioDto.profissionalNumero())
                .orElseThrow(() -> new ProfissionalDeSaudeNotFoundException("Profissional de saúde com o número de registro informado não foi encontrado."));

        prontuario.setDescricao(prontuarioDto.descricao());
        prontuario.setAlergias(prontuarioDto.alergias());
        prontuario.setObservacoes(prontuarioDto.observacoes());
        prontuario.setTipoSanguineo(prontuarioDto.tipoSanguineo());
        prontuario.setDoencasCronicas(prontuarioDto.doencasCronicas());
        prontuario.setHistoricoFamiliar(prontuarioDto.historicoFamiliar());

        prontuario.setProfissionalDeSaude(profissional);

        Prontuario atualizado = prontuarioRepository.save(prontuario);
        return prontuarioMapper.toResponse(atualizado);
    }

}
