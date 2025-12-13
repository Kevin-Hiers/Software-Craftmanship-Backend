package be.ucll.chappl.users.domain;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String username;
    private final String email;
    private final String password; // keep it simple for now

    public User(UUID id, String username, String email, String password) {
        if (id == null) throw new IllegalArgumentException("id required");
        this.id = id;
        this.username = require(username, "username");
        this.email = require(email, "email");
        this.password = require(password, "password");
    }

    public static User create(String username, String email, String password) {
        return new User(UUID.randomUUID(), username, email, password);
    }

    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }

    private static String require(String v, String name) {
        if (v == null || v.isBlank()) throw new IllegalArgumentException(name + " required");
        return v.trim();
    }
}
