package br.com.fiap.read_easy_api.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                    auth -> auth
                    .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                    .requestMatchers("/books/**").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
                )
                .build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        var user1 = User
                    .withUsername("samuel")
                    .password("$2a$12$xCeE3Akqi0MIJVuxBQLqL.vPDhl6/1Ujf2PEe0lvy15slqB8uhY2q")
                    .roles("USER")
                    .build();
        var user2 = User
                    .withUsername("peidao")
                    .password("$2a$12$v7iS8GDnxEIK1BylpjJqHuAhVDXYvzSktRmsBgf9FhJv/J6Aj.hfK")
                    .roles("ADMIN")
                    .build();

        var users = List.of(user1, user2);

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
