package com.tcc.ta_limpo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.ta_limpo.model.Veiculo;
import com.tcc.ta_limpo.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculos")
public class PageRestController {

    private final VeiculoService service;

    public PageRestController(VeiculoService service) {
        this.service = service;
    }

    @PatchMapping("/{id}/status")
    public Veiculo atualizarStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return service.atualizarStatus(id, status);
    }

    @PostMapping
    public Veiculo cadastrar(@RequestBody Veiculo veiculo) {
        return service.cadastrar(veiculo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping
    public List<Veiculo> listar() {
        return service.listar();
    }

    @GetMapping("/status/{status}")
    public List<Veiculo> listarPorStatus(@PathVariable String status) {
        return service.listarPorStatus(status);
    }
}
