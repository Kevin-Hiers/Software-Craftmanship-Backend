package be.ucll.chappl.users.application;

public record AuthResponse(
        UserInformation user,
        String token
) {}
