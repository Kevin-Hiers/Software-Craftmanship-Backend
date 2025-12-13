package be.ucll.chappl.users.application;

import be.ucll.chappl.users.domain.User;

import java.util.UUID;

public record UserInformation(UUID id, String username, String email) {
    public static UserInformation from(User u) {
        return new UserInformation(u.getId(), u.getUsername(), u.getEmail());
    }
}
