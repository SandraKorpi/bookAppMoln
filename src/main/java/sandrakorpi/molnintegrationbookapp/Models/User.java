package sandrakorpi.molnintegrationbookapp.Models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Representerar en användare i systemet.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String email;
    private String password; // Bör lagra haschat lösenord i databasen

    @ManyToMany
    @JoinTable(
            name = "user_favorite_books",  // Namn på join-tabellen
            joinColumns = @JoinColumn(name = "user_id"),  // Referens till användaren
            inverseJoinColumns = @JoinColumn(name = "book_id")  // Referens till boken
    )
    private Set<Book> favoriteBooks;


    // Standardkonstruktör
    public User() {}

    public User(String userName, String email, String password, Set<Book> favoriteBooks, Set<Book> booksToRead) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.favoriteBooks = favoriteBooks;
    }

    // UserDetails metoder
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Returnera behörigheter för användaren, kan implementeras om du har roller
        return List.of(); // Returnera en tom lista om det inte finns några roller
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Om kontot aldrig ska löpa ut
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Om kontot aldrig ska låsas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Om inloggningsuppgifter aldrig ska löpa ut
    }

    @Override
    public boolean isEnabled() {
        return true; // Om kontot alltid ska vara aktivt
    }

    // Getter och Setter metoder
    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Set<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(Set<Book> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }



    public void setPassword(final String password) {
        this.password = password;
    }
}
