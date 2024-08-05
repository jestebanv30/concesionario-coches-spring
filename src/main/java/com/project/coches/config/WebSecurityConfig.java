package com.project.coches.config;


import com.project.coches.exception.AccessDeniedHandlerException;
import com.project.coches.security.JwtAuthFilter;
import com.project.coches.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final AccessDeniedHandlerException accessDeniedHandlerException;

    private final JwtAuthFilter jwtAuthFilter;

    /**
     * Configura la seguridad de las peticiones HTTP
     * @param http Peticion a configurar
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())//Configuración predeterminada para el manejo de dominios externos. ej: carga de imágenes, etc. (Hay que permitirlo en caso tal se requiera, aunque subir la img desde el local, y una vez que el programa inicie, suministrarla con la ruta permitida
                .exceptionHandling(customizer -> customizer.accessDeniedHandler(accessDeniedHandlerException))// Si no tienes permiso, deniega el acceso y manda la excepcion
                //.exceptionHandling().accessDeniedHandler(accessDeniedHandlerException) EN DESUSO DESDE SPRING 6.1
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// SIN ESTADO
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/auth/**", "/swagger-ui.html/*", "/swagger-ui/*", "/v3/api-docs/**").permitAll() //, "/api/login/"
                                // auth roles to CUSTOMER
                                .requestMatchers(HttpMethod.GET, "/customers/").hasAnyRole(Roles.ADMIN, Roles.CUSTOMER)
                                .requestMatchers(HttpMethod.GET, "/customers/*").hasAnyRole(Roles.ADMIN, Roles.CUSTOMER)
                                .requestMatchers(HttpMethod.POST, "/customers").hasRole(Roles.ADMIN)
                                .requestMatchers(HttpMethod.PUT, "/customers").hasAnyRole(Roles.ADMIN, Roles.CUSTOMER)
                                .requestMatchers(HttpMethod.DELETE, "/customers/*").hasRole(Roles.ADMIN)
                                // auth roles to CARS
                                .requestMatchers(HttpMethod.GET, "/cars/").hasAnyRole(Roles.ADMIN, Roles.CUSTOMER)
                                .requestMatchers(HttpMethod.GET, "/cars/*").hasAnyRole(Roles.ADMIN, Roles.CUSTOMER)
                                .requestMatchers(HttpMethod.POST, "/cars").hasRole(Roles.ADMIN)
                                .requestMatchers(HttpMethod.PUT, "/cars").hasRole(Roles.ADMIN)
                                .requestMatchers(HttpMethod.DELETE, "/cars/*").hasRole(Roles.ADMIN)
                                // auth roles to CAR BRAND
                                .requestMatchers(HttpMethod.GET, "/car-brands/").hasAnyRole(Roles.ADMIN, Roles.CUSTOMER)
                                .requestMatchers(HttpMethod.GET, "/car-brands/*").hasAnyRole(Roles.ADMIN, Roles.CUSTOMER)
                                .requestMatchers(HttpMethod.POST, "/car-brands").hasRole(Roles.ADMIN)
                                .requestMatchers(HttpMethod.PUT, "/car-brands").hasRole(Roles.ADMIN)
                                .requestMatchers(HttpMethod.DELETE, "/car-brands/*").hasRole(Roles.ADMIN)
                                // auth roles to PURCHASE

                                //.requestMatchers("/customers/").permitAll()
                                //.requestMatchers(HttpMethod.DELETE, "/customers/").hasAuthority("ELIMINAR_PRIVILEGE")

                                //.requestMatchers(HttpMethod.GET, "/cars/").hasAnyRole(Roles.CUSTOMER, Roles.ADMIN)
                                //.requestMatchers(HttpMethod.POST, "/cars/").hasRole(Roles.ADMIN)
                                //.requestMatchers(HttpMethod.POST, "/car-brands").hasRole(Roles.CUSTOMER)
                                //.requestMatchers(HttpMethod.GET, "/car-brands").hasRole(Roles.CUSTOMER)
                                //.requestMatchers("/cars").hasAuthority("COMPRAR_PRIVILEGE")
                                //.requestMatchers("/customers").hasRole(Roles.ADMIN)

                                //solo toma el primer filtro, ya no se puede anidar un rol con una autoridad

                                //hasAuthority o hasRole para un solo rol/autoridad
                                //hasAnyAuthority para varios roles
                                .anyRequest().authenticated()

                ); //.formLogin(withDefaults());

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("https://concesionario-coches-front.vercel.app")); // Permite solo tu dominio de frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept")); // Especifica los encabezados permitidos
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /*
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(CorsConfiguration.ALL));
        configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
        configuration.setAllowedHeaders(List.of(CorsConfiguration.ALL));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    */
}
