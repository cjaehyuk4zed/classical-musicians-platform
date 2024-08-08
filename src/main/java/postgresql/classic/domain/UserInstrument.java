package postgresql.classic.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

@Entity(name = "user_instrument")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserInstrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "instrument")
    private String instrument;

    @Column(name = "level")
    private Long level;
}
