package com.project.Company.security;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component // This is our token manager, the gatekeeper of the JWT realm
public class JwtUtil {

    private final String SECRET_KEY = System.getenv("JWT_SECRET_KEY"); // Keep your secrets safe with environment variables (no hardcoding!)

    // Generate a token for the user—because who doesn’t love tokens?
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // The subject of our token, aka the email
                .setIssuedAt(new Date()) // When the token was created—time stamps are important
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Token expiration—1 hour validity
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Signing with a secure algorithm and our secret key
                .compact(); // Compact the token and send it off into the world
    }

    // Extract the email from a given token—because we need to know who's behind the token!
    public String extractEmail(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // Validate the token—if it’s expired or tampered with, it’s not valid anymore
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token); // Parse the token and check if it's legit
            return true; // Token is valid
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Invalid token, better luck next time!
        }
    }
}
