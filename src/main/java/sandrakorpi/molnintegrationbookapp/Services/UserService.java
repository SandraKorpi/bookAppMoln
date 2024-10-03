package sandrakorpi.molnintegrationbookapp.Services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sandrakorpi.molnintegrationbookapp.DTOs.UserDTO;
import sandrakorpi.molnintegrationbookapp.DTOs.UserRegistrationDto;
import sandrakorpi.molnintegrationbookapp.Models.Book;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Repositories.BookRepository;
import sandrakorpi.molnintegrationbookapp.Repositories.UserRepository;
import sandrakorpi.molnintegrationbookapp.exceptions.ResourceNotFoundException;
import sandrakorpi.molnintegrationbookapp.exceptions.UserAlreadyExistsException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BookRepository bookRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(int id) {
        return getUserOrFail(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(int id, UserRegistrationDto userRegistrationDto) {
        User existingUser = getUserOrFail(id);
        existingUser.setUserName(userRegistrationDto.getUserName());
        existingUser.setEmail(userRegistrationDto.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword())); // Kryptera lösenordet
        return userRepository.save(existingUser);
    }

    public void deleteUser(int id) {
        User userToDelete = getUserOrFail(id);
        userRepository.delete(userToDelete);
    }

    public User getUserOrFail(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no User with id " + id));
    }

    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUserName(), user.getEmail());
    }

    /**
     * Registrerar en ny användare.
     *
     * @param userRegistrationDto DTO med användarens registreringsinformation
     * @return Den registrerade användaren
     */
    public User register(UserRegistrationDto userRegistrationDto) {
        // Kontrollera om e-post redan finns
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use");
        }

        // Skapa en ny användare
        User user = new User();
        user.setUserName(userRegistrationDto.getUserName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword())); // Kryptera lösenordet

        return userRepository.save(user); // Spara användaren i databasen
    }

    public Set<Book> getFavoriteBooks(int id) {
        User findUser = getUserOrFail(id);
        return findUser.getFavoriteBooks();
    }

    public int getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();

                // Hämta användaren baserat på användarnamnet
                User user = userRepository.findByUserName(username)
                        .orElseThrow(() -> new ResourceNotFoundException("Användare hittades inte"));

                return user.getUserId();
            }
        }
        throw new SecurityException("Ingen inloggad användare");
    }
}