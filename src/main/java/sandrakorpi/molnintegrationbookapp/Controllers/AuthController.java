package sandrakorpi.molnintegrationbookapp.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sandrakorpi.molnintegrationbookapp.DTOs.JwtResponse;
import sandrakorpi.molnintegrationbookapp.DTOs.LoginUserDto;
import sandrakorpi.molnintegrationbookapp.DTOs.UserRegistrationDto;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Services.AuthenticationService;
import sandrakorpi.molnintegrationbookapp.Services.JwtService;
import sandrakorpi.molnintegrationbookapp.Services.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    private final JwtService jwtService;


    public AuthController(AuthenticationService authenticationService, UserService userService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.jwtService = jwtService;

    }

    // @PostMapping-annoteringen definierar en metod som hanterar POST-begäran till "/login".
    @PostMapping("/login")
    @Operation(summary = "log in", description = "Log in a user")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        // Kallar authenticate-metoden i AuthenticationService med loginUserDto och sparar den autentiserade användaren.
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        // Genererar en JWT-token för den autentiserade användaren.
        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Skapar en ny instans av LoginResponse med JWT-token och utgångstiden.
        JwtResponse loginResponse = new JwtResponse(jwtToken, authenticatedUser ,jwtService.getExpirationTime());

        // Returnerar en ResponseEntity med status 200 (OK) och loginResponse i kroppen.
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationDto registerUserDto) {
        User newUser = userService.register(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
