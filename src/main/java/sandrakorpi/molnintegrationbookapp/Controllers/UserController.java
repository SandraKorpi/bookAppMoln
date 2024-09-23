package sandrakorpi.molnintegrationbookapp.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sandrakorpi.molnintegrationbookapp.DTOs.UserDTO;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Operationer relaterade till användare")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Hämta alla användare", description = "Hämtar en lista med alla användare.")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{email}")
    @Operation(summary = "Hämta användare med specifik e-post", description = "Hämtar en användare baserat på den angivna e-postadressen.")
    public ResponseEntity<UserDTO> findUserByEmail(@PathVariable String email) {
        UserDTO foundUser = userService.findUserByEmail(email);
        return ResponseEntity.ok().body(foundUser);
    }
}
