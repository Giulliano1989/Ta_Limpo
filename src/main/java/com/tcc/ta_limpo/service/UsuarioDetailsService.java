package com.tcc.ta_limpo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

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
