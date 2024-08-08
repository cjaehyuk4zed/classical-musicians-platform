package postgresql.classic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInstrumentDTO {

    @Schema(example = "Bob")
    private String userId;

    @Schema(example = "Violin")
    private String instrument;

    @Schema(example = "5", description = "Level of proficiency, between 1-10")
    private Long level;

}
