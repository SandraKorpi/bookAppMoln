package sandrakorpi.molnintegrationbookapp.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sandrakorpi.molnintegrationbookapp.DTOs.LoginUserDto;
import sandrakorpi.molnintegrationbookapp.DTOs.UserRegistrationDto;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Repositories.UserRepository;
import sandrakorpi.molnintegrationbookapp.exceptions.UserAlreadyExistsException;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserService userService
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public User authenticate(LoginUserDto input) {
        try {
            // Kontrollera autentiseringen
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );

            // Hämta användaren efter autentisering
            User user = userRepository.findByEmail(input.getEmail());
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return user;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    // Exempel på registreringsmetod
    public User register(UserRegistrationDto input) {
        // Kontrollera om e-post redan finns
        if (userRepository.existsByEmail(input.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use");
        }

        // Skapa ny användare
        User newUser = new User();
        newUser.setEmail(input.getEmail());
        newUser.setPassword(passwordEncoder.encode(input.getPassword()));
        // Sätt andra fält om det behövs

        return userRepository.save(newUser);
    }
}
