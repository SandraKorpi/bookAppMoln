package sandrakorpi.molnintegrationbookapp.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sandrakorpi.molnintegrationbookapp.DTOs.JwtResponse;
import sandrakorpi.molnintegrationbookapp.DTOs.LoginUserDto;
import sandrakorpi.molnintegrationbookapp.DTOs.UserRegistrationDto;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Security.JwtTokenProvider;
import sandrakorpi.molnintegrationbookapp.Services.AuthenticationService;
import sandrakorpi.molnintegrationbookapp.Services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider; // Lägg till JwtTokenProvider som dependency

    public AuthController(AuthenticationService authenticationService, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider; // Inject JwtTokenProvider
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto) {
        Authentication authentication = authenticationService.authenticate(loginUserDto);  // Returnerar Authentication-objekt

        // Generera JWT token med injected JwtTokenProvider
        String token = jwtTokenProvider.generateToken(authentication);

        // Returnera token och användarinformation
        User user = (User) authentication.getPrincipal(); // Hämtar användardetaljer
        return ResponseEntity.ok(new JwtResponse(token, user));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationDto registerUserDto) {
        User newUser = userService.register(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
