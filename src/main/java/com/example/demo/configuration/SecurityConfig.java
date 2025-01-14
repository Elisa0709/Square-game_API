package com.example.demo.configuration;


import com.example.demo.util.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * Cette classe configure la sécurité HTTP, l'authentification des utilisateurs,
 * la gestion des mots de passe et les utilisateurs en mémoire. Elle repose sur Spring Security
 * et est annotée avec {@code @Configuration} et {@code @EnableWebSecurity} pour activer les
 * mécanismes de sécurité.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;


    public SecurityConfig(UserDetailsService userDetailsService, AuthenticationConfiguration
            authenticationConfiguration, JwtFilter jwtFilter) {

        this.userDetailsService = userDetailsService;
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configure la sécurité HTTP de l'application en définissant les règles d'accès aux différentes URL.
     * Cette méthode permet de personnaliser la gestion de la sécurité en activant ou désactivant certaines protections
     * et en définissant les permissions d'accès pour les utilisateurs.
     * La méthode :
     * - Désactive la protection CSRF (utile pour les API sans état).
     * - Autorise toutes les requêtes dont l'URL commence par "/auth/" à être accessibles sans authentification (utilisées pour l'authentification, la connexion, etc.).
     * - Exige une authentification pour toutes les autres requêtes, ce qui signifie que l'utilisateur doit être authentifié pour y accéder.
     *
     * @param http l'objet HttpSecurity permettant de configurer les règles de sécurité HTTP.
     * @return un objet SecurityFilterChain qui contient les configurations de sécurité.
     * @throws Exception si une erreur se produit lors de la configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/test/admin").hasRole("ADMIN")
                        .requestMatchers("/test/user").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


    /**
     * Fournit un fournisseur d'authentification basé sur un service utilisateur personnalisé et un encodeur de mot de passe.
     *
     * @return un AuthenticationProvider configuré pour l'authentification via une base de données ou un service utilisateur personnalisé.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() { //
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Fournit un encodeur de mot de passe qui délègue l'encodage à l'algorithme par défaut choisi
     * (généralement `BCryptPasswordEncoder`).
     * Il peut également supporter plusieurs algorithmes d'encodage via un encodeur par délégation.
     *
     * @return un PasswordEncoder pour l'encodage et la vérification des mots de passe.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Fournit un gestionnaire d'authentification (AuthenticationManager) à partir de la configuration
     * d'authentification existante.
     *
     * @return un AuthenticationManager pour gérer l'authentification des utilisateurs.
     * @throws Exception si un problème survient lors de la récupération du gestionnaire.
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    // Fake users in memory
    @Bean
    public static UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin123")
                        .roles("ADMIN")
                        .build());
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user123")
                        .roles("USER")
                        .build()
        );
        return userDetailsManager;
    }


}


