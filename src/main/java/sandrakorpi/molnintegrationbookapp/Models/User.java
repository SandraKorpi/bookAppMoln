package sandrakorpi.molnintegrationbookapp.Models;

import java.util.List;

/**
 * Representerar en användare i systemet.
 */
public class User {
    private int userId;
    private String userName;
    private String email;
    private String password; // Bör lagra haschat lösenord i databasen
    private List<Book> bookList;

    /**
     * Konstruktor för att skapa en ny användare.
     *
     * @param userId ID för användaren
     * @param userName Användarnamn
     * @param email E-postadress
     * @param password Lösenord
     * @param bookList Lista över böcker som användaren har
     */
    public User(final int userId, final String userName, final String email,
                final String password, final List<Book> bookList) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.bookList = bookList;
    }

    /**
     * Hämtar användar-ID.
     *
     * @return användar-ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sätter användar-ID.
     *
     * @param userId användar-ID att sätta
     */
    public void setUserId(final int userId) {
        this.userId = userId;
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
     * Hämtar lista över böcker som användaren har.
     *
     * @return lista över böcker
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Sätter lista över böcker som användaren har.
     *
     * @param bookList lista över böcker att sätta
     */
    public void setBookList(final List<Book> bookList) {
        this.bookList = bookList;
    }
}
