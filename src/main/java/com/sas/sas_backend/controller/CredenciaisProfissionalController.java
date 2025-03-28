package com.sas.sas_backend.controller;

import com.sas.sas_backend.dtos.CredenciaisProfissionalDto;
import com.sas.sas_backend.service.CredenciaisProfissionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/credenciais-Profissional")
@RequiredArgsConstructor
public class CredenciaisProfissionalController {
    private final CredenciaisProfissionalService credenciaisProfissionalService;

    @PostMapping("/create")
    public ResponseEntity<CredenciaisProfissionalDto> cadastrarCredenciaisProfissional(@RequestBody @Valid CredenciaisProfissionalDto credenciaisProfissionalDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(credenciaisProfissionalService.cadastrarCredenciaisProfissional(credenciaisProfissionalDto));
    }

    @GetMapping
    public ResponseEntity<List<CredenciaisProfissionalDto>> buscarTodos() {
        return ResponseEntity.ok().body(credenciaisProfissionalService.buscarTodos());
    }

    @GetMapping("/findByNumeroRegistro/{numeroRegistro}")
    public ResponseEntity<CredenciaisProfissionalDto> buscarPorNumeroRegistro(@PathVariable String numeroRegistro) {

        return ResponseEntity.ok().body(credenciaisProfissionalService.buscarPorNumeroRegistro(numeroRegistro));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CredenciaisProfissionalDto> atualizarCredenciaisProfissional(@PathVariable String id, @Valid @RequestBody CredenciaisProfissionalDto credenciaisProfissionalDto) {
        return ResponseEntity.status(HttpStatus.OK).body(credenciaisProfissionalService.atualizarCredenciaisProfissional(id, credenciaisProfissionalDto));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarCredenciaisProfissional(@PathVariable String id) {
        credenciaisProfissionalService.deletarCredenciaisProfissional(id);
        return ResponseEntity.noContent().build();
    }
}
