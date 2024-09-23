package sandrakorpi.molnintegrationbookapp.DTOs;

/**
 * Dataöverföringsobjekt (DTO) för användarinloggning.
 */
public class UserDTO {
    private String email;

    private String userName;

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
    public UserDTO(final String email, final String userName) {
        this.email = email;
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

    public String getUserName(){
        return userName;
    }

    public void setUserName(final String userName){
        this.userName = userName;
    }

}
