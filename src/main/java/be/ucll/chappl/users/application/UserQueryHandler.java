package be.ucll.chappl.users.application;

import be.ucll.chappl.users.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserQueryHandler {
    private final UserRepository repo;

    public UserQueryHandler(UserRepository repo) {
        this.repo = repo;
    }

    public UserInformation getById(UUID id) {
        return repo.findById(id).map(UserInformation::from)
                .orElseThrow(() -> new NoSuchElementException("user not found"));
    }
}
