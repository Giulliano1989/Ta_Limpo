package com.tcc.ta_limpo.service;

import com.tcc.ta_limpo.model.Veiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VeiculoService {

    private final List<Veiculo> veiculos = new ArrayList<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    public List<Veiculo> listar() {
        return veiculos;
    }

    public List<Veiculo> listarPorStatus(String status) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getStatus().equals(status))
                .toList();
    }

    public Veiculo cadastrar(Veiculo veiculo) {
        veiculo.setId(proximoId.getAndIncrement());
        veiculo.setStatus("aguardando");

        veiculos.add(veiculo);

        return veiculo;
    }

    public void excluir(Long id) {
        veiculos.removeIf(veiculo -> veiculo.getId().equals(id));
    }

    public Veiculo atualizarStatus(Long id, String novoStatus) {
        Veiculo veiculo = veiculos.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        if (!novoStatus.equals("aguardando")
                && !novoStatus.equals("lavando")
                && !novoStatus.equals("finalizado")) {
            throw new RuntimeException("Status inválido");
        }

        veiculo.setStatus(novoStatus);

        return veiculo;
    }
}