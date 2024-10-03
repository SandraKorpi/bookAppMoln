package sandrakorpi.molnintegrationbookapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sandrakorpi.molnintegrationbookapp.Models.User;
import sandrakorpi.molnintegrationbookapp.Repositories.UserRepository;

import java.util.ArrayList;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Hämta användaren från databasen baserat på användarnamnet
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Användaren " + username + " hittades inte"));

        // Skapa och returnera ett UserDetails-objekt baserat på användaren
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

}
