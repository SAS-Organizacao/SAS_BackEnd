package com.sas.sas_backend.controller;

import com.sas.sas_backend.dtos.ExameDto;
import com.sas.sas_backend.service.ExameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exame")
@RequiredArgsConstructor
public class ExameController {
    private final ExameService exameService;


    @PostMapping("/create")
    public ResponseEntity<ExameDto> cadastrarExame(@RequestBody @Valid ExameDto exameDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exameService.cadastrarExame(exameDto));
    }

    @GetMapping
    public ResponseEntity<List<ExameDto>> listarExames() {
        return ResponseEntity.ok().body(exameService.listarExames());
    }

    @GetMapping("findBy/paciente/cpf/{cpf}")
    public ResponseEntity<List<ExameDto>> listarExamesPacienteCPF(@PathVariable String cpf) {

        return ResponseEntity.ok().body(exameService.listarExamesPacienteCPF(cpf));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExameDto> atualizarPaciente(@PathVariable String id, @Valid @RequestBody ExameDto exameDto) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.atualizarExame(id, exameDto));

    }


    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Void> removerExame(@PathVariable String id) {
        exameService.removerExame(id);
        return ResponseEntity.noContent().build();
    }
}


