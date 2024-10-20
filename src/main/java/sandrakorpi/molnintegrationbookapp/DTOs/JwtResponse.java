package sandrakorpi.molnintegrationbookapp.DTOs;

import sandrakorpi.molnintegrationbookapp.Models.User;

public class JwtResponse {
    private String token;
    private User user;
    private long expiresIn;

    public JwtResponse(String token, User user, long expiresIn) {
        this.token = token;
        this.user = user;
        this.expiresIn = expiresIn; //utgångstiden
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
