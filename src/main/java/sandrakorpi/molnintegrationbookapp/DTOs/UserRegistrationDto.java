package sandrakorpi.molnintegrationbookapp.DTOs;

/**
 * Dataöverföringsobjekt (DTO) för användarregistrering.
 */
public class UserRegistrationDto {
    private String userName;
    private String email;
    private String password; // Lösenordet kommer att hashas innan det sparas

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(
            final String userName,
            final String email,
            final String password
    ) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
