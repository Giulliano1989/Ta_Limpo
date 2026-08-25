package com.tcc.ta_limpo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String fazerLogin(
            @RequestParam String usuario,
            @RequestParam String senha) {

        // Aqui será feita a validação

        return "redirect:/inicio";
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/veiculos")
    public String veiculos() {
        return "veiculos";
    }

    @GetMapping("/status")
    public String status() {
        return "status";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
