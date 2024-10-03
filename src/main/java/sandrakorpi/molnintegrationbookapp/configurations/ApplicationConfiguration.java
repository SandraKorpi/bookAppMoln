package sandrakorpi.molnintegrationbookapp.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Repositories.UserRepository;

import java.util.Optional;

// Annotation för att ange att denna klass innehåller konfiguration för Spring.
@Configuration
public class ApplicationConfiguration {

    private final UserRepository userRepository;

    // Konstruktor för injektion av UserRepository.
    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Bean som definierar UserDetailsService, och hämtar användare från databasen baserat på e-post.
    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUserName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
            return user;
        };
    }


    // Bean för att hantera lösenordskryptering med BCryptPasswordEncoder.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean för att hantera AuthenticationManager.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Bean för DaoAuthenticationProvider som hanterar autentisering via UserDetailsService och PasswordEncoder.
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}