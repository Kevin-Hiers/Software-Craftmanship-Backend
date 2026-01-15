package be.ucll.chappl.unit.support;

import be.ucll.chappl.users.application.PasswordHasher;

public class FakePasswordHasher implements PasswordHasher {

    @Override
    public String hash(String rawPassword) {
        return "HASHED:" + rawPassword;
    }

    @Override
    public boolean matches(String rawPassword, String hash) {
        return hash(rawPassword).equals(hash);
    }
}
