package sandrakorpi.molnintegrationbookapp.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.secret-key}") // Läs hemligheten från application.properties
    private String jwtSecret;
    private final int jwtExpirationMs;

    public JwtTokenProvider(@Value("${jwt.expirationMs}") int jwtExpirationMs) {
        this.jwtExpirationMs = jwtExpirationMs;
      //  this.jwtSecret = generateJwtSecret(); // Generera hemligheten vid instansiering
    }

    // Generera en säker JWT-hemlighet
   /* private String generateJwtSecret() {
        byte[] secretBytes = new byte[64];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secretBytes);
        return Base64.getEncoder().encodeToString(secretBytes);
    } */

    // Generera JWT-token
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Extrahera användarnamn från token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Validera JWT-token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Hantera undantag, exempelvis token utgången eller ogiltig
            return false;
        }
    }
}
