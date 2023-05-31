package io.igorcossta.security.config;

import io.igorcossta.security.user.Permission;
import io.igorcossta.security.user.Role;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static io.igorcossta.security.user.Permission.*;
import static io.igorcossta.security.user.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(manager -> manager.requestMatchers("/api/v1/auth/**").permitAll())

//                user endpoints
                .authorizeHttpRequests(manager -> manager.requestMatchers("/api/v1/user/**")
                        .hasAnyRole(ADMIN.name(), USER.name()))

                .authorizeHttpRequests(manager -> manager.requestMatchers(GET, "/api/v1/user/**")
                        .hasAnyAuthority(USER_READ.name(), ADMIN_READ.name()))

                .authorizeHttpRequests(manager -> manager.requestMatchers(POST, "/api/v1/user/**")
                        .hasAnyAuthority(USER_CREATE.name(), ADMIN_CREATE.name()))

                .authorizeHttpRequests(manager -> manager.requestMatchers(DELETE, "/api/v1/user/**")
                        .hasAnyAuthority(USER_DELETE.name(), ADMIN_DELETE.name()))

//                admin endpoints
                .authorizeHttpRequests(manager -> manager.requestMatchers("/api/v1/admin/**")
                        .hasRole(ADMIN.name()))
                .authorizeHttpRequests(manager -> manager.requestMatchers(GET, "/api/v1/admin/**")
                        .hasAuthority(ADMIN_READ.name()))

                .authorizeHttpRequests(manager -> manager.requestMatchers(POST, "/api/v1/admin/**")
                        .hasAuthority(ADMIN_CREATE.name()))

                .authorizeHttpRequests(manager -> manager.requestMatchers(DELETE, "/api/v1/admin/**")
                        .hasAuthority(ADMIN_DELETE.name()))

                .authorizeHttpRequests(manager -> manager.anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
