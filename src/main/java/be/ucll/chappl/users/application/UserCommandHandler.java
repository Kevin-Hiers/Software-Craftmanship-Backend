package be.ucll.chappl.users.application;

import be.ucll.chappl.users.commands.RegisterUserCommand;
import be.ucll.chappl.users.domain.User;
import be.ucll.chappl.users.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandler {
    private final UserRepository repo;

    public UserCommandHandler(UserRepository repo) {
        this.repo = repo;
    }

    public UserInformation handle(RegisterUserCommand cmd) {
        var user = User.create(cmd.username(), cmd.email(), cmd.password());
        repo.save(user);
        return UserInformation.from(user);
    }
}
