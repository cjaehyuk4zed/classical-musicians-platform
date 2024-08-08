package postgresql.classic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import postgresql.classic.domain.UserInstrument;

import java.util.List;
import java.util.Optional;

public interface UserInstrumentRepository extends JpaRepository<UserInstrument, Long> {
    List<UserInstrument> findAllByUserId(String userId);

    Optional<UserInstrument> findByUserIdAndInstrument(String userId, String instrument);

    List<UserInstrument> findAllByInstrumentAndLevelBetween(String instrument, Long level1, Long level2);
}
