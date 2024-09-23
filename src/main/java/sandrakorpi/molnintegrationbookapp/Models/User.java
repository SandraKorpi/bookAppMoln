package sandrakorpi.molnintegrationbookapp.Models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * Representerar en användare i systemet.
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String email;
    private String password; // Bör lagra haschat lösenord i databasen

    /**
     * Listan över böcker som användaren har som favoriter.
     *
     * @ManyToMany: Anger att varje användare kan ha flera favoritböcker och varje bok kan vara favorit för flera användare.
     * @JoinTable: Definierar den join-tabell som kommer att användas för att lagra denna relation.
     * - name: Namnet på join-tabellen (user_favorite_books).
     * - joinColumns: Kolumnen i join-tabellen som refererar till den aktuella entiteten (user_id).
     * - inverseJoinColumns: Kolumnen i join-tabellen som refererar till den relaterade entiteten (book_id).
     */
    @ManyToMany
    @JoinTable(
            name = "user_favorite_books",  // Namn på join-tabellen som kopplar användare och favoritböcker
            joinColumns = @JoinColumn(name = "user_id"),  // Kolumn som refererar till användaren i join-tabellen
            inverseJoinColumns = @JoinColumn(name = "book_id")  // Kolumn som refererar till boken i join-tabellen
    )
    private Set<Book> favoriteBooks;

    /**
     * Listan över böcker som användaren vill läsa i framtiden.
     *
     * @ManyToMany: Anger att varje användare kan ha flera böcker som de vill läsa och varje bok kan vara på flera användares att-läsa-lista.
     * @JoinTable: Definierar den join-tabell som kommer att användas för att lagra denna relation.
     * - name: Namnet på join-tabellen (user_books_to_read).
     * - joinColumns: Kolumnen i join-tabellen som refererar till den aktuella entiteten (user_id).
     * - inverseJoinColumns: Kolumnen i join-tabellen som refererar till den relaterade entiteten (book_id).
     */
    @ManyToMany
    @JoinTable(
            name = "user_books_to_read",  // Namn på join-tabellen som kopplar användare och böcker de vill läsa
            joinColumns = @JoinColumn(name = "user_id"),  // Kolumn som refererar till användaren i join-tabellen
            inverseJoinColumns = @JoinColumn(name = "book_id")  // Kolumn som refererar till boken i join-tabellen
    )
    private Set<Book> booksToRead;

    /**
     * Konstruktor för att skapa en ny användare.
     *
     * @param userName Användarnamn
     * @param email E-postadress
     * @param password Lösenord
     * @param favoriteBooks Lista över böcker som användaren tycker om
     * @param booksToRead Lista över böcker som användaren vill läsa.
     */
    public User(
            final String userName,
            final String email,
            final String password,
            final Set<Book> favoriteBooks,
            final Set<Book> booksToRead
    ) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.favoriteBooks = favoriteBooks;
        this.booksToRead = booksToRead;
    }

    // Standardkonstruktör

    public User() {

    }

    /**
     * Hämtar användarnamn.
     *
     * @return användarnamn
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Hämtar användarid..
     *
     * @return användarId.
     */
    public int getUserId(){
        return userId;
    }

    /**
     * Sätter användarnamn.
     *
     * @param userName användarnamn att sätta
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Hämtar e-postadress.
     *
     * @return e-postadress
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sätter e-postadress.
     *
     * @param email e-postadress att sätta
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Hämtar lösenord.
     *
     * @return lösenord
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sätter lösenord.
     *
     * @param password lösenord att sätta
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Hämtar lista över böcker som användaren har läst.
     *
     * @return lista över böcker
     */
    public Set<Book> getFavoriteBooks() {
        return favoriteBooks;
    }
    /**
     * Sätter listan över favoritböcker för användaren.
     *
     * @param favoriteBooks Set av böcker som användaren har som favorit
     */
    public void setFavoriteBooks(Set<Book> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    /**
     * Hämtar listan över böcker som användaren vill läsa i framtiden.
     *
     * @return Set av böcker som användaren vill läsa
     */
    public Set<Book> getBooksToRead() {
        return booksToRead;
    }


    /**
     * Sätter lista över böcker som användaren vill läsa i framtiden.
     *
     * @param booksToRead lista över böcker att sätta
     */
    public void setBooksToRead(final Set<Book> booksToRead) {
        this.booksToRead = booksToRead;
    }

}
