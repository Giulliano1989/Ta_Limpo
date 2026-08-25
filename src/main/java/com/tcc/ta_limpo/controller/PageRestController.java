package com.tcc.ta_limpo.controller;

import com.tcc.ta_limpo.model.Veiculo;
import com.tcc.ta_limpo.service.VeiculoService;
import org.springframework.web.bind.annotation.*;

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
}