package be.ucll.chappl.users.application;

import be.ucll.chappl.users.domain.User;

public interface AuthTokenService {
    String generate(User user);
}
