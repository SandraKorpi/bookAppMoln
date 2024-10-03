package sandrakorpi.molnintegrationbookapp.Security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import sandrakorpi.molnintegrationbookapp.Services.JwtService;

import java.io.IOException;

// @Component-annotering för att markera denna klass som en Spring-komponent.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Privat fält för att hålla en instans av HandlerExceptionResolver.
    private final HandlerExceptionResolver handlerExceptionResolver;
    // Privat fält för att hålla en instans av JwtService.
    private final JwtService jwtService;
    // Privat fält för att hålla en instans av UserDetailsService.
    private final UserDetailsService userDetailsService;

    // Konstruktor för att injicera beroendena JwtService, UserDetailsService och HandlerExceptionResolver.
    @Autowired
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        // Tilldelar den injicerade JwtService till det privata fältet.
        this.jwtService = jwtService;
        // Tilldelar den injicerade UserDetailsService till det privata fältet.
        this.userDetailsService = userDetailsService;
        // Tilldelar den injicerade HandlerExceptionResolver till det privata fältet.
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    // Override för att implementera doFilterInternal-metoden.
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // Användarens HTTP-begäran.
            @NonNull HttpServletResponse response, // HTTP-svaret som ska skickas tillbaka.
            @NonNull FilterChain filterChain // Kedjan av filter som begäran passerar igenom.
    ) throws ServletException, IOException {
        // Hämtar Authorization-headern från begäran.
        final String authHeader = request.getHeader("Authorization");

        // Om Authorization-headern är null eller inte börjar med "Bearer " (standardprefix för JWT).
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Fortsätter med nästa filter i kedjan utan att göra något.
            filterChain.doFilter(request, response);
            return; // Avslutar metoden här om det inte finns en giltig JWT-token.
        }

        try {
            // Extraherar JWT-token från Authorization-headern.
            final String jwt = authHeader.substring(7);
            // Använder JwtService för att extrahera användarnamnet från JWT-token.
            final String userEmail = jwtService.extractUsername(jwt);

            // Hämtar aktuell autentiseringsinformation från säkerhetskontexten.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Om användarnamnet finns och det inte redan finns en autentisering.
            if (userEmail != null && authentication == null) {
                // Laddar användardetaljer från UserDetailsService med hjälp av användarnamnet.
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // Om JWT-token är giltig för de laddade användardetaljerna.
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // Skapar en UsernamePasswordAuthenticationToken för den autentiserade användaren.
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, // Användarens detaljer.
                            null, // Inga autentiseringsuppgifter (lösenord) behövs här.
                            userDetails.getAuthorities() // Användarens roller och rättigheter.
                    );

                    // Sätter autentiseringsdetaljer från begäran.
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Sätter autentiseringen i säkerhetskontexten.
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // Fortsätter med nästa filter i kedjan.
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            // Hanterar undantag genom att skicka dem till HandlerExceptionResolver.
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}