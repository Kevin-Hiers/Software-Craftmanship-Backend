package be.ucll.chappl.leftovers.application;

import be.ucll.chappl.leftovers.infrastructure.LeftoverRepository;
import be.ucll.chappl.users.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class LeftoverQueryHandler {

    private final LeftoverRepository leftovers;
    private final UserRepository users;

    public LeftoverQueryHandler(LeftoverRepository leftovers, UserRepository users) {
        this.leftovers = leftovers;
        this.users = users;
    }

    public LeftoverInformation getById(UUID leftoverId) {
        return leftovers.findById(leftoverId)
                .map(LeftoverInformation::from)
                .orElseThrow(() -> new NoSuchElementException("leftover not found"));
    }

    public List<LeftoverInformation> listForUser(UUID userId) {
        users.findById(userId).orElseThrow(() -> new NoSuchElementException("user not found"));
        return leftovers.findByUserId(userId).stream().map(LeftoverInformation::from).toList();
    }
}
