package com.ftn.sbnz.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ftn.sbnz.service.security.jwt.AuthenticationTokenFilter;
import com.ftn.sbnz.service.security.jwt.EntryPointUnauthorizedHandler;
import com.ftn.sbnz.service.security.jwt.TokenUtils;
import com.ftn.sbnz.service.services.implementations.ClientService;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new ClientService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // this will call loadUserByUsername() method from this service
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    // performs user authentication for us
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Autowired
    private TokenUtils tokenUtils;

    // We define access rights for requests to specific URLs/paths
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // all unauthenticated requests uniformly process and send a 401 error
        http.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler);
        http.authorizeRequests()
            .antMatchers("/client/login").permitAll()	// TODO dodati putanje kojima korisnik moze da pristupa bez autentifikacije (logovanje, registracija)

                // for every other request the user must be authenticated
                .anyRequest().authenticated().and()
                // apply configuration for CORS from WebConfig class
                .cors().and()

                // add custom filter AuthenticationTokenFilter which check JWT token
                .addFilterBefore(new AuthenticationTokenFilter(tokenUtils,  userDetailsService()), BasicAuthenticationFilter.class);

        http.csrf().disable();
        http.headers().frameOptions().disable();

        // authentication chaining
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    // for paths that do not need authorization/authentication
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(HttpMethod.POST, "/client/login");
                // .antMatchers(HttpMethod.GET, "/api/passenger/activate/{activationId}", "/api/user/{id}/resetPassword", "/", "/webjars/**", "/*.html", "favicon.ico",
                //         "/**/*.html", "/**/*.css", "/**/*.js")
                // .antMatchers(HttpMethod.PUT, "/api/user/{id}/resetPassword");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000"));
        config.setAllowCredentials(true);
        config.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
