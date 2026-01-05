package be.ucll.chappl.users.application;

import be.ucll.chappl.users.commands.LoginUserCommand;
import be.ucll.chappl.users.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserLoginHandler {

    private final UserRepository repo;
    private final PasswordHasher hasher;
    private final AuthTokenService tokens;

    public UserLoginHandler(
            UserRepository repo,
            PasswordHasher hasher,
            AuthTokenService tokens
    ) {
        this.repo = repo;
        this.hasher = hasher;
        this.tokens = tokens;
    }

    public AuthResponse handle(LoginUserCommand cmd) {
        var user = repo.findByEmail(cmd.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!hasher.matches(cmd.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        var token = tokens.generate(user);
        return new AuthResponse(UserInformation.from(user), token);
    }
}
