package com.estoque.peixaria.controller;

import com.estoque.peixaria.model.MovimentacaoDiaria;
import com.estoque.peixaria.service.MovimentacaoDiariaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoDiariaController {

    private final MovimentacaoDiariaService service;

    public MovimentacaoDiariaController(MovimentacaoDiariaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovimentacaoDiaria> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public MovimentacaoDiaria criar(@RequestBody MovimentacaoDiaria movimentacao) {
        return service.salvar(movimentacao);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}