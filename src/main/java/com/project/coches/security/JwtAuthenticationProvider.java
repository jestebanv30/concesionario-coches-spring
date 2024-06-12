package com.project.coches.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.coches.domain.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Clae encargada de la creacion yvalidacion del jwt para el inicio de sesion de un usuario
 */
@Component
public class JwtAuthenticationProvider {

    /**
     * Llave para cifrar el jwt
     */
    @Value("${jwt.secret.key}")
    private String secretKey;

    /**
     * Lista blanca con los jwt creados
     */
    private HashMap<String, CustomerDto> listToken = new HashMap<>();

    /**
     * Crea un nuevo jwt en base al cliente recibido y a la agrega a la listaBlanca (listToken)
     * @param customerDtoJwt cliente recibido para la creacion del token
     * @return Jwt creado
     */
    public String createToken(CustomerDto customerDtoJwt) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hora en milisegundos

        Algorithm algorithm = Algorithm.HMAC256(secretKey); // Algoritmo de encriptación y el HMAC necesita una llave secetra

        String tokenCreated = JWT.create()
                .withClaim("cardId", customerDtoJwt.getCardId())
                .withClaim("fullName", customerDtoJwt.getFullName())
                .withClaim("email", customerDtoJwt.getEmail())
                .withClaim("numberCellPhone", String.valueOf(customerDtoJwt.getNumberCellphone()))
                .withClaim("rol", customerDtoJwt.getRol())
                .withIssuedAt(now) //Fecha de creacion
                .withExpiresAt(validity) //Fecha de expiracion
                .sign(algorithm); //Firma criptográficamente del JWT


        listToken.put(tokenCreated, customerDtoJwt);
        return tokenCreated;
    }

    /**
     * Valida si el token es valido y retorna una sesion del usuario
     * @param token Token a validar
     * @return Sesion del usuario
     * @BadCredentialsException Si el token no existe en la lista blanca
     */
    public Authentication validateToken(String token) throws AuthenticationException {

        // Verifica si el token está en la lista de tokens revocados
        if (revokedTokens.contains(token)) {
            throw new BadCredentialsException("Token revocado");
        }
        //Verfica el token como su firma y expiracion, lanza una excepcion si algo falla
        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);

        CustomerDto exist = listToken.get(token);

        if (exist == null) {
            throw new BadCredentialsException("Usuario no registrado");
        }

        HashSet<SimpleGrantedAuthority> rolesAndAuthorities = new HashSet<>();
        rolesAndAuthorities.add(new SimpleGrantedAuthority("ROLE_"+exist.getRol()));
        //rolesAndAuthorities.add(new SimpleGrantedAuthority("ROLE_"+Roles.ADMIN));

        //Después de validar el token me autentico y eso lo guardo en el Context
        return new UsernamePasswordAuthenticationToken(exist, token, rolesAndAuthorities);
    }

    // Almacenar en un set los tokens revocados para evitar duplicados
    private Set<String> revokedTokens = new HashSet<>();

    public String deleteToken(String token){
/**
        String[] authElements = token.split(" ");
        String validToken = authElements[1].trim();

        System.out.println("Imprimiendo token a eliminar:");
        System.out.println(validToken);
        System.out.println("Imprimiendo token asociado a la lista blanca:");
        for (String tokenlist : listToken.keySet()) {
            System.out.println(tokenlist);
        }
        System.out.println(listToken.containsKey(validToken));
 */
        if (!listToken.containsKey(token)){
            return "No existe token";
        }

        revokedTokens.add(token);
        listToken.remove(token);

        return "Sesión cerrada exitosamente";
    }
}
