package be.ucll.chappl.users.application;

import be.ucll.chappl.users.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserExistenceQueryHandler {

    private final UserRepository repo;

    public UserExistenceQueryHandler(UserRepository repo) {
        this.repo = repo;
    }

    public boolean existsByEmail(String email) {
        return repo.findByEmail(email).isPresent();
    }
}
