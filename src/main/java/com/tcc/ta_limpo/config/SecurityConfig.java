package com.tcc.ta_limpo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**")
                )
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/assets/**"
                ).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/veiculos/**")
                .hasAnyRole("ADMIN", "LAVADOR", "CONSULTOR")
                .requestMatchers("/veiculos")
                .hasAnyRole("ADMIN", "CONSULTOR")
                .requestMatchers("/status")
                .hasAnyRole("ADMIN", "LAVADOR", "CONSULTOR")
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("usuario")
                .passwordParameter("senha")
                .defaultSuccessUrl("/inicio", true)
                .failureUrl("/login?erro=true")
                .permitAll()
                )
                .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
