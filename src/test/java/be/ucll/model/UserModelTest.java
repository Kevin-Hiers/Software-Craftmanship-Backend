package be.ucll.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserModelTest {

    @Autowired
    private Validator validator;

    private final String validUsername = "test";
    private final String validEmail = "test@test.org";
    private final String validPassword = "test123";

    @Test
    void givenValidUser_whenValidate_thenNoViolations() {
        User user = new User(validUsername, validEmail, validPassword);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertTrue(violations.isEmpty());
    }

    @Test
    void givenEmptyUsername_whenValidate_thenUsernameViolation() {
        User user = new User("", validEmail, validPassword);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("username", violation.getPropertyPath().toString());
        assertEquals("Username cannot be empty", violation.getMessage());
    }

    @Test
    void givenBlankUsername_whenValidate_thenUsernameViolation() {
        User user = new User("   ", validEmail, validPassword);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("username", violation.getPropertyPath().toString());
        assertEquals("Username cannot be empty", violation.getMessage());
    }

    @Test
    void givenEmptyEmail_whenValidate_thenEmailViolation() {
        User user = new User(validUsername, "", validPassword);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("Email cannot be empty", violation.getMessage());
    }

    @Test
    void givenBlankEmail_whenValidate_thenEmailViolation() {
        User user = new User(validUsername, "   ", validPassword);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("Email cannot be empty", violation.getMessage());
    }

    @Test
    void givenEmptyPassword_whenValidate_thenPasswordViolation() {
        User user = new User(validUsername, validEmail, "");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("password", violation.getPropertyPath().toString());
        assertEquals("Password cannot be empty", violation.getMessage());
    }

    @Test
    void givenBlankPassword_whenValidate_thenPasswordViolation() {
        User user = new User(validUsername, validEmail, "   ");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("password", violation.getPropertyPath().toString());
        assertEquals("Password cannot be empty", violation.getMessage());
    }
}
