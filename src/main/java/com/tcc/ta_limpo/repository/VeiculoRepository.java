package com.tcc.ta_limpo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tcc.ta_limpo.model.Veiculo;

@Repository
public class VeiculoRepository {

    private final JdbcTemplate jdbcTemplate;

    public VeiculoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Veiculo> listar() {
        String sql = """
                SELECT
                    lv.id,
                    ma.nome AS marca,
                    ve.modelo,
                    lv.placa,
                    lv.status
                FROM lavagem lv
                INNER JOIN veiculo ve
                    ON ve.id = lv.veiculo_id
                INNER JOIN marca ma
                    ON ma.id = ve.marca_id
                ORDER BY lv.id
                """;

        return jdbcTemplate.query(sql, (resultado, numeroLinha) -> {
            Veiculo veiculo = new Veiculo();

            veiculo.setId(resultado.getLong("id"));
            veiculo.setMarca(resultado.getString("marca"));
            veiculo.setModelo(resultado.getString("modelo"));
            veiculo.setPlaca(resultado.getString("placa"));
            veiculo.setStatus(resultado.getString("status"));

            return veiculo;
        });
    }

    public List<Veiculo> listarPorStatus(String status) {
        String sql = """
            SELECT
                lv.id,
                ma.nome AS marca,
                ve.modelo,
                lv.placa,
                lv.status
            FROM lavagem lv
            INNER JOIN veiculo ve
                ON ve.id = lv.veiculo_id
            INNER JOIN marca ma
                ON ma.id = ve.marca_id
            WHERE lv.status = ?
            ORDER BY lv.id
            """;

        return jdbcTemplate.query(
                sql,
                (resultado, numeroLinha) -> {
                    Veiculo veiculo = new Veiculo();

                    veiculo.setId(resultado.getLong("id"));
                    veiculo.setMarca(resultado.getString("marca"));
                    veiculo.setModelo(resultado.getString("modelo"));
                    veiculo.setPlaca(resultado.getString("placa"));
                    veiculo.setStatus(resultado.getString("status"));

                    return veiculo;
                },
                status
        );
    }

    public int atualizarStatus(Long id, String status) {
        String sql = "UPDATE lavagem SET status = ? WHERE id = ?";

        return jdbcTemplate.update(sql, status, id);
    }

    public Veiculo cadastrar(Veiculo veiculo) {
        KeyHolder marcaKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var statement = connection.prepareStatement(
                    "INSERT INTO marca (nome) VALUES (?) "
                            + "ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id)",
                    new String[] { "id" }
            );
            statement.setString(1, veiculo.getMarca());
            return statement;
        }, marcaKeyHolder);

        Long marcaId = marcaKeyHolder.getKey().longValue();

        KeyHolder veiculoKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var statement = connection.prepareStatement(
                    "INSERT INTO veiculo (modelo, marca_id) VALUES (?, ?)",
                    new String[] { "id" }
            );
            statement.setString(1, veiculo.getModelo());
            statement.setLong(2, marcaId);
            return statement;
        }, veiculoKeyHolder);

        KeyHolder lavagemKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var statement = connection.prepareStatement(
                    "INSERT INTO lavagem (veiculo_id, placa, status) "
                            + "VALUES (?, ?, 'aguardando')",
                    new String[] { "id" }
            );
            statement.setLong(1, veiculoKeyHolder.getKey().longValue());
            statement.setString(2, veiculo.getPlaca());
            return statement;
        }, lavagemKeyHolder);

        return listarPorId(lavagemKeyHolder.getKey().longValue());
    }

    public int excluir(Long id) {
        return jdbcTemplate.update("DELETE FROM lavagem WHERE id = ?", id);
    }

    private Veiculo listarPorId(Long id) {
        return jdbcTemplate.queryForObject(
                """
                SELECT lv.id, ma.nome AS marca, ve.modelo, lv.placa, lv.status
                FROM lavagem lv
                INNER JOIN veiculo ve ON ve.id = lv.veiculo_id
                INNER JOIN marca ma ON ma.id = ve.marca_id
                WHERE lv.id = ?
                """,
                (resultado, numeroLinha) -> {
                    Veiculo resultadoVeiculo = new Veiculo();
                    resultadoVeiculo.setId(resultado.getLong("id"));
                    resultadoVeiculo.setMarca(resultado.getString("marca"));
                    resultadoVeiculo.setModelo(resultado.getString("modelo"));
                    resultadoVeiculo.setPlaca(resultado.getString("placa"));
                    resultadoVeiculo.setStatus(resultado.getString("status"));
                    return resultadoVeiculo;
                },
                id
        );
    }

}
