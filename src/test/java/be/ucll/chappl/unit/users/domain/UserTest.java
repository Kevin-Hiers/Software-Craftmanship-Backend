package be.ucll.chappl.unit.users.domain;

import be.ucll.chappl.users.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void givenValidUser_whenCreated_thenHasIdUsernameAndEmail() {
        var user = User.create("stefanie", "stefanie@ucll.be", "pw");

        assertNotNull(user.getId());
        assertEquals("stefanie", user.getUsername());
        assertEquals("stefanie@ucll.be", user.getEmail());
    }

    @Test
    void givenBlankUsername_whenCreatingUser_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> User.create(" ", "a@b.com", "pw"));
    }

    @Test
    void givenBlankEmail_whenCreatingUser_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> User.create("u", " ", "pw"));
    }

    @Test
    void givenBlankPassword_whenCreatingUser_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> User.create("u", "a@b.com", " "));
    }
}