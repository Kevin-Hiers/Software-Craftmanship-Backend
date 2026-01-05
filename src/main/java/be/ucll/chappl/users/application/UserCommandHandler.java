package be.ucll.chappl.users.application;

import be.ucll.chappl.users.commands.RegisterUserCommand;
import be.ucll.chappl.users.domain.User;
import be.ucll.chappl.users.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandler {

    private final UserRepository repo;
    private final PasswordHasher hasher;

    public UserCommandHandler(UserRepository repo, PasswordHasher hasher) {
        this.repo = repo;
        this.hasher = hasher;
    }

    public UserInformation handle(RegisterUserCommand cmd) {
        var passwordHash = hasher.hash(cmd.password());
        var user = User.create(cmd.username(), cmd.email(), passwordHash);
        repo.save(user);
        return UserInformation.from(user);
    }
}
