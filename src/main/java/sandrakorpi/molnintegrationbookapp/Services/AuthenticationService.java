package sandrakorpi.molnintegrationbookapp.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sandrakorpi.molnintegrationbookapp.DTOs.LoginUserDto;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Repositories.UserRepository;
import sandrakorpi.molnintegrationbookapp.exceptions.ResourceNotFoundException;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUserName(),
                        input.getPassword()
                )
        );

        User user = userRepository.findByUserName(input.getUserName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + input.getUserName()));

        return user;
    }


}

