package com.sas.sas_backend.Controller;

import com.sas.sas_backend.Dtos.PacienteDto;
import com.sas.sas_backend.Service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;


    @PostMapping("/create")
    public ResponseEntity<PacienteDto> cadastrarPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.cadastrarPaciente(pacienteDto));
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> buscarTodos() {
        return ResponseEntity.ok().body(pacienteService.buscarTodos());
    }

    @GetMapping("/findByCpf/{cpf}")
    public ResponseEntity<PacienteDto> buscarPorCpf(@PathVariable String cpf) {

        return ResponseEntity.ok().body(pacienteService.buscarPorCpf(cpf));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PacienteDto> atualizarPaciente(@PathVariable UUID id, @Valid @RequestBody PacienteDto pacienteDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.atualizarPaciente(id, pacienteDto));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable UUID id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
