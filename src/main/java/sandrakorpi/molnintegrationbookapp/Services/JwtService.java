package sandrakorpi.molnintegrationbookapp.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Hemlig nyckel som används för att signera JWT, injiceras från applikationskonfigurationen.
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    // Utgångstid för JWT, injiceras från applikationskonfigurationen.
    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    // Metod för att extrahera användarnamn från en JWT.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Generisk metod för att extrahera ett specifikt krav (claim) från en JWT.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Metod för att generera en JWT för en användare.
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Metod för att generera en JWT med extra krav (claims) för en användare.
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    // Metod för att få utgångstiden för JWT.
    public long getExpirationTime() {
        return jwtExpiration;
    }

    // Privat metod för att bygga en JWT med specificerade krav (claims) och utgångstid.
    // Extra krav (claims) kan inkluderas i JWT för att ange ytterligare information om användaren.
    // Exempelvis användarens roll, behörigheter, etc. { "role": "admin", "permissions": ["read", "write"]};
    // UserDetails innehåller användarinformation som används för att skapa JWT.
    // Utgångstiden bestämmer hur länge JWT är giltig.
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder() // Skapar en ny JWT.
                .setClaims(extraClaims) // Lägger till extra krav (claims) i JWT.
                .setSubject(userDetails.getUsername()) // Sätter användarnamnet som ämne för JWT.
                .setIssuedAt(new Date(System.currentTimeMillis())) // Sätter utfärdandetiden för JWT.
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Sätter utgångstiden för JWT.
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Signerar JWT med hemlig nyckel.
                .compact(); // Komprimerar JWT till en sträng.
    }

    // Metod för att kontrollera om en JWT är giltig för en specifik användare.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Privat metod för att kontrollera om en JWT har gått ut.
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Privat metod för att extrahera utgångstiden från en JWT.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Privat metod för att extrahera alla krav (claims) från en JWT.
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder() // Skapar en ny JWT-parser.
                .setSigningKey(getSignInKey()) // Sätter signeringsnyckeln för JWT.
                .build() // Bygger JWT-parsern.
                .parseClaimsJws(token) // Parsar JWT och returnerar dess krav (claims).
                .getBody(); // Returnerar kraven (claims) från JWT.
    }

    // Privat metod för att få signeringsnyckeln från den Base64-avkodade hemliga nyckeln.
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}