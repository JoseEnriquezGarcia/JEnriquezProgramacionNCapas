package com.digis01JEnriquezProgramacionNCapas.Configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    
//    @Bean
//    public UserDetailsService userDetailService() throws Exception {
//        UserDetails programador = User.builder()
//                .username("programador")
//                .password("{noop}12345")
//                .roles("PROGRAMADOR")
//                .build();
//        
//        UserDetails administrador = User.builder()
//                .username("admin")
//                .password("{noop}12345")
//                .roles("ADMIN")
//                .build();
//        
//        UserDetails analista = User.builder()
//                .username("analista")
//                .password("{noop}12345")
//                .roles("ANALISTA")
//                .build();
//        
//        return new InMemoryUserDetailsManager(programador, administrador, analista);
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        
        httpSecurity.authorizeHttpRequests(auth -> auth
                
                .requestMatchers("/usuario")
                .hasAnyAuthority("Sistemas", "Administrador", "Cliente")
                
                .requestMatchers("/usuario/CargaMasiva")
                .hasAnyAuthority("Sistemas", "Administrador")
                
                .requestMatchers(HttpMethod.GET, "/usuario/**")
                .hasAnyAuthority("Sistemas", "Administrador")
                
                .requestMatchers("/usuario/**")
                .hasAuthority("Sistemas")
                
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
    
    @Bean
    public UserDetailsService jdbcUserDetails(DataSource dataSource){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        manager.setUsersByUsernameQuery("select Username, Password, Status from Usuario where Username = ?");
        manager.setAuthoritiesByUsernameQuery("select Username, NombreRol from RolManager where Username = ?");
        
        return manager;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
