package sandrakorpi.molnintegrationbookapp.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService; // Används för att hämta användardata från databasen
    private final PasswordEncoder passwordEncoder; // Används för att validera lösenord

    @Autowired
    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); // Hämtar användarnamn från inloggningen
        String password = (String) authentication.getCredentials(); // Hämtar lösenord från inloggningen

        // Laddar användardetaljer från databasen baserat på användarnamnet
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Kontrollerar om användaren finns och om lösenordet matchar
        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            // Om autentiseringen lyckas, returnera en autentiseringstoken med användarens rättigheter
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        }
        // Om autentiseringen misslyckas, kasta ett undantag
        throw new AuthenticationException("Invalid username or password") {};
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Anger att denna provider stöder UsernamePasswordAuthenticationToken
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
