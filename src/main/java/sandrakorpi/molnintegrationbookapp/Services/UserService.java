package sandrakorpi.molnintegrationbookapp.Services;

import org.springframework.stereotype.Service;
import sandrakorpi.molnintegrationbookapp.DTOs.UserDTO;
import sandrakorpi.molnintegrationbookapp.DTOs.UserRegistrationDto;
import sandrakorpi.molnintegrationbookapp.Models.Book;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Repositories.BookRepository;
import sandrakorpi.molnintegrationbookapp.Repositories.UserRepository;
import sandrakorpi.molnintegrationbookapp.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class UserService {

    public final UserRepository userRepository;

    public final BookRepository bookRepository;

    /**
     * Konstruktor för att skapa en UserService.
     *
     * @param userRepository Repository för att hantera användardata
     */
    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Hämtar en användare baserat på deras ID.
     *
     * @param id Användarens ID
     * @return Användaren med det angivna ID
     */
    public User getUserById(int id) {
        return getUserOrFail(id);
    }

    /**
     * Hämtar alla användare.
     *
     * @return Lista över alla användare
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Uppdaterar en användare baserat på deras ID.
     *
     * @param id                 Användarens ID
     * @param userRegistrationDto DTO med användarens uppdaterade information
     * @return Den uppdaterade användaren
     */
    public User updateUser(int id, UserRegistrationDto userRegistrationDto) {
        User existingUser = getUserOrFail(id);
        existingUser.setUserName(userRegistrationDto.getUserName());
        existingUser.setEmail(userRegistrationDto.getEmail());
        existingUser.setPassword(userRegistrationDto.getPassword());
        return userRepository.save(existingUser);
    }

    /**
     * Tar bort en användare baserat på deras ID.
     *
     * @param id Användarens ID
     */
    public void deleteUser(int id) {
        User userToDelete = getUserOrFail(id);
        userRepository.delete(userToDelete);
    }

    /**
     * Hämtar en användare baserat på deras ID eller kastar ett undantag om användaren inte hittas.
     *
     * @param id Användarens ID
     * @return Användaren med det angivna ID
     * @throws ResourceNotFoundException Om användaren inte finns
     */
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
    }}
