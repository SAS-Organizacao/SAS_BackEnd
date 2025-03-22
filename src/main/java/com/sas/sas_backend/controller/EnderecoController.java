package com.sas.sas_backend.controller;

import com.sas.sas_backend.Dtos.EnderecoDto;
import com.sas.sas_backend.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/Endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("/create")
    public ResponseEntity<EnderecoDto> cadastrarEndereco(@RequestBody @Valid EnderecoDto enderecoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.cadastrarEndereco(enderecoDto));
    }

    @PutMapping("/update/Endereco/{id}")
    public ResponseEntity<EnderecoDto> atualizarEndereco(@PathVariable UUID id, @Valid @RequestBody EnderecoDto enderecoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.atualizarEndereco(id, enderecoDto));

    }

    @DeleteMapping("/delete/Endereco/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable UUID id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
