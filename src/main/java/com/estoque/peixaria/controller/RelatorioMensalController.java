package com.estoque.peixaria.controller;

import com.estoque.peixaria.model.RelatorioMensal;
import com.estoque.peixaria.repository.RelatorioMensalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios-mensais")
public class RelatorioMensalController {

    private final RelatorioMensalRepository repository;

    public RelatorioMensalController(RelatorioMensalRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<RelatorioMensal> listarRelatoriosMensais() {
        return repository.findAll();
    }
}