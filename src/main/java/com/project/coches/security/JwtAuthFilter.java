package com.project.coches.security;


import com.project.coches.exception.typesexceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Filtro que valida si la peticion tiene la cbezera de Autorizacion
 */
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter { //OncePerRequestFilter clase abstracta que permite la personalización del filtro

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * Lista blanca de URIs que salta o que voy a permitir el acceso
     */
    private List<String> urlsToSkip = List.of("/auth", "/swagger-ui.html", "/swagger-ui", "/api-docs"); //"/api/login"

    /**
     * Verifica si a la URI no se le debe aplicar el filtro
     * @param request current HTTP request Petición a validar
     * @return True la URI existe en la lista blanca, false de lo contrario
     * @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println("en esta peticion se rompe");
        System.out.println(request.getRequestURI());

        //Devuelve true en caso de que esté en la listablanca, son URIs que estarán exentas del proceso de autenticación y false en caso contrario.
        return urlsToSkip.stream().anyMatch(url -> request.getRequestURI().contains(url));
    }


    /**
     * Valida si la petición contiene la cabezera de authorization con el bearer token y más validaciones con respecto al token
     * @param request
     * @param response
     * @param filterChain es la cadena de filtros que se configuró en WebSecurityConfig (SecurityFilterChain) y se le pasa la autenticación del token
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Extraigo el token de la cabecera. EJ: "Bearer eyJhbGciOiJ...."
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null) {
            throw new UnauthorizedException();
        }

        String[] authElements = header.split(" ");

        if (authElements.length != 2 || !"Bearer".equals(authElements[0])) {
            throw new UnauthorizedException();
        }

        try {
            Authentication auth = jwtAuthenticationProvider.validateToken(authElements[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("voy a imprimir el context");
            System.out.println(SecurityContextHolder.getContext());
            System.out.println("voy a imprimir la autenticacion");
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
        } catch (RuntimeException e) {
            SecurityContextHolder.clearContext();
            System.out.println("se estalló");
            System.out.println(e);
            throw new RuntimeException(e);
        }
        System.out.println("llegué aqui");

        filterChain.doFilter(request, response);
    }

}
