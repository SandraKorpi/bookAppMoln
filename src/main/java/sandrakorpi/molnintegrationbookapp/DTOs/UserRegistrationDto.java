package sandrakorpi.molnintegrationbookapp.DTOs;

/**
 * Dataöverföringsobjekt (DTO) för användarregistrering.
 */
public class UserRegistrationDto {
    private String userName;
    private String email;
    private String password; // Lösenordet kommer att hashas innan det sparas

    /**
     * Standardkonstruktor.
     */
    public UserRegistrationDto() {
    }

    /**
     * Konstruktor för att skapa ett nytt UserRegistrationDto-objekt.
     *
     * @param userName Användarnamn
     * @param email E-postadress
     * @param password Lösenord
     */
    public UserRegistrationDto(final String userName, final String email, final String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
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
}
