package com.digis01JEnriquezProgramacionNCapas.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    
    @Bean
    public UserDetailsService userDetailService() throws Exception {
        UserDetails programador = User.builder()
                .username("programador")
                .password("{noop}12345")
                .roles("programador")
                .build();
        
        UserDetails administrador = User.builder()
                .username("admin")
                .password("{noop}12345")
                .roles("administrador")
                .build();
        
        UserDetails analista = User.builder()
                .username("analista")
                .password("{noop}12345")
                .roles("analista")
                .build();
        
        return new InMemoryUserDetailsManager(programador, administrador, analista);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/usuario/Form/**")
                .hasRole("analista")
                
                .requestMatchers("/usuario/CargaMasiva")
                .hasRole("administrador")
                
                .requestMatchers("/usuario")
                .hasAnyRole("administrador", "analista")
                
                .requestMatchers("/usuario/**")
                .hasRole("programador")
                
        )
                .formLogin(login -> {
                    login.loginProcessingUrl("/login")
                            .permitAll()
                            .defaultSuccessUrl("/usuario", true);
                })
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout"));

        return httpSecurity.build();
    }
}
