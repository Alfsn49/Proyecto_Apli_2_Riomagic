package com.aliencode.rms_backend.CapaSeguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private Key jwtSecretKey;
    private int jwtExpiration = 3600;

    @PostConstruct
    public void init() {
        jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generarTokenJWT(String subject, Claims claims) {
        Date fechaExpiracion = new Date(System.currentTimeMillis() + jwtExpiration * 1000);

        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(fechaExpiracion)
                .signWith(jwtSecretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}