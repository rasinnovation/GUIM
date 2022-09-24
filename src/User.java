import java.util.UUID;

public class User {
    private final UUID id;
    private final String username;
    private final String password;

    public User (String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public User (UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getIdString() {
        return id.toString();
    }

    public String getPassword() {
        return password;
    }
}
