package be.ucll.chappl.groups.application;

import be.ucll.chappl.groups.domain.User;

import java.util.UUID;

public record UserInformation(UUID id, String username, String email) {
    static UserInformation from(User u) {
        return new UserInformation(u.getId(), u.getUsername(), u.getEmail());
    }
}
