package be.ucll.chappl.groups.domain;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String username;
    private final String email;
    private final String password;

    public User(UUID id, String username, String email, String password) {
        this.id = Objects.requireNonNull(id, "id is required");
        this.username = requireNonBlank(username, "username");
        this.email = requireNonBlank(email, "email");
        this.password = requireNonBlank(password, "password");
    }

    public static User create(String username, String email, String password) {
        return new User(UUID.randomUUID(), username, email, password);
    }

    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }

    private static String requireNonBlank(String v, String name) {
        if (v == null || v.isBlank()) throw new IllegalArgumentException(name + " is required");
        return v.trim();
    }
}
