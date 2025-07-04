package com.smtcoders.api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
//    private final String SECRET_KEY = "mztcoders";
//
//    public String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
//  .signWith(SignatureAlgorithm.HS256, "mztcoders")
//                .compact();
//    }
//
//    public String extractEmail(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//    public String extractRole(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
//                .getBody().get("role", String.class);
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String username = extractEmail(token);
//        return username.equals(userDetails.getUsername()) && !isExpired(token);
//    }
//
//    private boolean isExpired(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
//                .getBody().getExpiration().before(new Date());
//    }

    private final String SECRET_KEY = "mi-clave-secreta-super-segura-de-al-menos-32-bytes"; // debe ser >= 32 bytes

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // Generar un token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Obtener el username (subject) desde el token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Verificar si el token expiró
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validar token
    public boolean validateToken(String token, String expectedUsername) {
        final String username = extractUsername(token);
        return (username.equals(expectedUsername) && !isTokenExpired(token));
    }

    // Helpers

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}


