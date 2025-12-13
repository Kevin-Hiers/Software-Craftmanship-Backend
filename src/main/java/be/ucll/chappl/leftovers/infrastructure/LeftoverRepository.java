package be.ucll.chappl.leftovers.infrastructure;

import be.ucll.chappl.leftovers.domain.Leftover;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeftoverRepository {
    void save(Leftover leftover);
    Optional<Leftover> findById(UUID id);
    List<Leftover> findByUserId(UUID userId);
    void deleteById(UUID id);
}
