package be.ucll.chappl.users.infrastructure;

import be.ucll.chappl.users.domain.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Primary
public class PostgresUserRepository implements UserRepository {

    private final JpaUserJpaRepository jpa;

    public PostgresUserRepository(JpaUserJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(User user) {
        jpa.save(UserEntity.fromDomain(user));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpa.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpa.findByEmailIgnoreCase(email)
                .map(UserEntity::toDomain);
    }
}
