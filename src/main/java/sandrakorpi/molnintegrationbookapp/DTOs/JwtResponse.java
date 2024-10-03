package sandrakorpi.molnintegrationbookapp.DTOs;

import sandrakorpi.molnintegrationbookapp.Models.User;

public class JwtResponse {
    private String token;
    private User user;
    private long expiresIn; // Nytt fält för utgångstiden

    public JwtResponse(String token, User user, long expiresIn) {
        this.token = token;
        this.user = user;
        this.expiresIn = expiresIn; // Tilldela utgångstiden
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public long getExpiresIn() {
        return expiresIn; // Getter för utgångstiden
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn; // Setter för utgångstiden
    }
}
