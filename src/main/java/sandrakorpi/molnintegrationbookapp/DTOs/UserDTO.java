package sandrakorpi.molnintegrationbookapp.DTOs;

/**
 * Dataöverföringsobjekt (DTO) för användarinloggning.
 */
public class UserDTO {
    private String email;
    private String password;

    /**
     * Standardkonstruktor.
     */
    public UserDTO() {
    }

    /**
     * Konstruktor för att skapa ett nytt UserDTO-objekt.
     *
     * @param email e-postadress för användarinloggning
     * @param password lösenord för användarinloggning
     */
    public UserDTO(final String email, final String password) {
        this.email = email;
        this.password = password;
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
