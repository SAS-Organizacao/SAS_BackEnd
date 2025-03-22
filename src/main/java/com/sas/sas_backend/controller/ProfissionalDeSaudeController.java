package com.sas.sas_backend.controller;

import com.sas.sas_backend.Dtos.ProfissionalDeSaudeDto;
import com.sas.sas_backend.service.ProfissionalDeSaudeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profissional-de-saude")
@RequiredArgsConstructor
public class ProfissionalDeSaudeController {
    private final ProfissionalDeSaudeService service;

    @PostMapping("/profissional-de-saude/create")
    public ResponseEntity<ProfissionalDeSaudeDto> cadastrarProfissionalDeSaude(@RequestBody @Valid ProfissionalDeSaudeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarProfissionalDeSaude(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalDeSaudeDto>> buscarTodos() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }

    @GetMapping("/findByNome/profissionaldesaude/{nome}")
    public ResponseEntity<ProfissionalDeSaudeDto> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(service.buscarPorNome(nome));
    }

    @PutMapping("/update/profissionaldesaude/{id}")
    public ResponseEntity<ProfissionalDeSaudeDto> atualizarProfissionalDeSaude(@PathVariable UUID id, @Valid @RequestBody ProfissionalDeSaudeDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarProfissionalDeSaude(id, dto));
    }

    @DeleteMapping("/delete/profissionaldesaude/{id}")
    public ResponseEntity<Void> deletarProfissionalDeSaude(@PathVariable UUID id) {
        service.deletarProfissionalDeSaude(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/buscar-paciente-por-cpf/{cpf}")
//    public ResponseEntity<PacienteDto> buscarPacientePorCpf(@PathVariable String cpf){
//        return ResponseEntity.ok(service.buscarPacientePorCpf(cpf));
//    }


}
