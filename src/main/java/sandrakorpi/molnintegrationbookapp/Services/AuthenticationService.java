package sandrakorpi.molnintegrationbookapp.Services;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

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

    public Authentication authenticate(LoginUserDto loginUserDto) {
        // Skapa en authentication token från inloggningsuppgifterna
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.getUserName(), loginUserDto.getPassword());

        // Autentisera användaren med AuthenticationManager
        return authenticationManager.authenticate(authenticationToken);
    }

}