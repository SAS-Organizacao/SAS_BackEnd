package com.sas.sas_backend.service;

import com.sas.sas_backend.Dtos.CredenciaisProfissionalDto;
import com.sas.sas_backend.Exceptions.CredenciaisProfissional.CredenciaisProfissionalAlreadyExistsException;
import com.sas.sas_backend.Exceptions.CredenciaisProfissional.CredenciaisProfissionalNotFoundException;
import com.sas.sas_backend.Models.CredenciaisProfissional;
import com.sas.sas_backend.mappers.CredenciaisProfissionalMapper;
import com.sas.sas_backend.repository.CredenciaisProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CredenciaisProfissionalService {
    private final CredenciaisProfissionalMapper credenciaisProfissionalMapper;
    private final CredenciaisProfissionalRepository credenciaisProfissionalRepository;

    public CredenciaisProfissionalDto cadastrarCredenciaisProfissional(CredenciaisProfissionalDto dto) {
        credenciaisProfissionalRepository.findByNumeroRegistro(dto.numeroRegistro()).ifPresent(credencialProfissional -> {
            throw new CredenciaisProfissionalAlreadyExistsException("Numero de Registro ja cadastrado !");
        });
        credenciaisProfissionalRepository.findByEmail(dto.email()).ifPresent(credencialProfissional -> {
            throw new CredenciaisProfissionalAlreadyExistsException("Email já cadastrado!");
        });

        CredenciaisProfissional credencialProfissional = credenciaisProfissionalMapper.tocredenciaisProfissional(dto);
        return credenciaisProfissionalMapper.toCredenciaisProfissionalDto(credenciaisProfissionalRepository.save(credencialProfissional));
    }

    public List<CredenciaisProfissionalDto> buscarTodos() {
        List<CredenciaisProfissional> listcredencialProfissional = credenciaisProfissionalRepository.findAll();
        if (listcredencialProfissional.isEmpty()) {
            throw new CredenciaisProfissionalNotFoundException("Lista não encontrada");
        }
        return listcredencialProfissional.stream().map(credenciaisProfissionalMapper::toCredenciaisProfissionalDto).collect(Collectors.toList());

    }

    public CredenciaisProfissionalDto buscarPorNumeroRegistro(String numeroRegistro) {

        CredenciaisProfissional credencialProfissionalNumeroRegistro = credenciaisProfissionalRepository.findByNumeroRegistro(numeroRegistro).orElseThrow(() -> new CredenciaisProfissionalNotFoundException(" numero de registro:" + numeroRegistro));
        return credenciaisProfissionalMapper.toCredenciaisProfissionalDto(credencialProfissionalNumeroRegistro);

    }

    public CredenciaisProfissionalDto atualizarCredenciaisProfissional(String id, CredenciaisProfissionalDto dto) {
        CredenciaisProfissional credenciaisProfissionalExistente = credenciaisProfissionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("credenciais do Profissional não encontrado."));
        CredenciaisProfissional credenciaisProfissional = credenciaisProfissionalMapper.tocredenciaisProfissional(dto);
        credenciaisProfissional.setIdCredencial(credenciaisProfissionalExistente.getIdCredencial());
        return credenciaisProfissionalMapper.toCredenciaisProfissionalDto(credenciaisProfissionalRepository.save(credenciaisProfissional));
    }


    public void deletarCredenciaisProfissional(String id) {
        credenciaisProfissionalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Credencial não encontrada."));
        credenciaisProfissionalRepository.deleteById(id);

    }

}
