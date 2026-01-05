package be.ucll.chappl.users.infrastructure;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
class UserEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    protected UserEntity() {}

    UserEntity(UUID id, String username, String email, String passwordHash) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    static UserEntity fromDomain(be.ucll.chappl.users.domain.User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPasswordHash()
        );
    }

    be.ucll.chappl.users.domain.User toDomain() {
        return new be.ucll.chappl.users.domain.User(
                id,
                username,
                email,
                passwordHash
        );
    }
}
