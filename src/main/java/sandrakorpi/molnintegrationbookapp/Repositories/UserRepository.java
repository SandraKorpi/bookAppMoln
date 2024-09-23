package sandrakorpi.molnintegrationbookapp.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandrakorpi.molnintegrationbookapp.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Metod för att hitta användare baserat på e-post
    User findByEmail(String email);

    boolean existsByEmail(String email);
}

