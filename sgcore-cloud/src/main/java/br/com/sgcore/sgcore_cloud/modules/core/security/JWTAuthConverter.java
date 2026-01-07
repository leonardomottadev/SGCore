package br.com.sgcore.sgcore_cloud.modules.core.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class JWTAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");

        if (realmAccess == null || !realmAccess.containsKey("roles")) {
            return new JwtAuthenticationToken(jwt, Collections.emptyList());
        }

        Collection<String> roles = realmAccess.get("roles");

        var grants = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+ role)).toList();

        return new JwtAuthenticationToken(jwt, grants);
    }
}