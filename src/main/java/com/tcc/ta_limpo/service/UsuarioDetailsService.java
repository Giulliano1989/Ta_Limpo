package com.tcc.ta_limpo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// UserDetailsService Informa que essa classe e responsavel por localizar usuarios
@Service
public class UsuarioDetailsService implements UserDetailsService {
//JdbcTemplate permiite executar comandos sql

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override

    //UserDetails tranforma o registro do banco de dados no formato que o spring security entende
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // ? no username e para que o sql nao concatene texto diretamente.
        String sql = """
                SELECT username, password, permissao
                FROM usuario
                WHERE username = ?
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNumber) -> User
                            .withUsername(resultSet.getString("username"))
                            .password(resultSet.getString("password"))
                            .roles(resultSet.getString("permissao").toUpperCase())
                            .build(),
                    username
            );
        } catch (Exception exception) {
            throw new UsernameNotFoundException(
                    "Usuário não encontrado: " + username
            );
        }
    }
}
