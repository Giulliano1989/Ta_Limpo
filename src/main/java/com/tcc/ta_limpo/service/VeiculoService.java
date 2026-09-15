package com.tcc.ta_limpo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcc.ta_limpo.model.Veiculo;
import com.tcc.ta_limpo.repository.VeiculoRepository;

@Service
public class VeiculoService {

    public List<Veiculo> listarPorStatus(String status) {
        return repository.listarPorStatus(status);
    }

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public List<Veiculo> listar() {
        return repository.listar();
    }

    public Veiculo cadastrar(Veiculo veiculo) {
        return repository.cadastrar(veiculo);
    }

    public void excluir(Long id) {
        if (repository.excluir(id) == 0) {
            throw new RuntimeException("Lavagem não encontrada");
        }
    }

    public Veiculo atualizarStatus(Long id, String novoStatus) {
        if (!novoStatus.equals("aguardando")
                && !novoStatus.equals("lavando")
                && !novoStatus.equals("finalizado")) {
            throw new RuntimeException("Status inválido");
        }

        int linhasAlteradas = repository.atualizarStatus(id, novoStatus);

        if (linhasAlteradas == 0) {
            throw new RuntimeException("Veículo não encontrado");
        }

        return repository.listar().stream()
                .filter(veiculo -> veiculo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

}
