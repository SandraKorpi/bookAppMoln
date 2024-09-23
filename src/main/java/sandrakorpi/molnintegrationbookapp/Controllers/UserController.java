package sandrakorpi.molnintegrationbookapp.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sandrakorpi.molnintegrationbookapp.DTOs.UserDTO;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Repositories.UserRepository;
import sandrakorpi.molnintegrationbookapp.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> findUserByEmail(@PathVariable String email) {
        UserDTO foundUser = userService.findUserByEmail(email);
        return ResponseEntity.ok().body(foundUser);
    }
    }
