package postgresql.classic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import postgresql.classic.domain.UserInstrument;
import postgresql.classic.dto.UserInstrumentDTO;
import postgresql.classic.service.UserService;

import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user")
@Tag(name = "UsersV1", description = "유저 기능 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 악기 추가", description = "유저의 악기를 추가합니다.")
    @PostMapping("/instrument")
    public ResponseEntity<String> addInstrument(@RequestBody UserInstrumentDTO userInstrumentDTO) {
        String result = userService.addUserInstrument(userInstrumentDTO);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "유저 악기 목록 조회", description = "유저의 악기를 전부 조회합니다.")
    @GetMapping(value = "/{userId}/instruments", produces = "application/json")
    public ResponseEntity<List<UserInstrument>> getUserInstruments(@PathVariable(value = "userId") String userId){
        List<UserInstrument> userInstruments = userService.getUserInstruments(userId);

        log.info("UserInstruments: {}", userInstruments);
        log.info("UserInstruments size: {}", userInstruments.size());

        return ResponseEntity.ok().body(userInstruments);
    }

    @ApiResponse(responseCode = "200", description = "UserInstrument found", content = @Content(schema = @Schema(implementation = UserInstrument.class)))
    @Operation(summary = "유저 악기 조회", description = "유저의 특정 악기를 조회합니다.")
    @GetMapping(value = "/{userId}/instruments/{instrument}", produces = "application/json")
    public ResponseEntity<UserInstrument> getUserInstrument(@PathVariable(value = "userId") String userId,
                                                        @PathVariable(value = "instrument") String instrument){
        UserInstrument userInstrument = userService.getUserInstrument(userId, instrument);

        log.info("UserInstrument: {}", userInstrument);

        return ResponseEntity.ok().body(userInstrument);
    }

    @Operation(summary = "악기 실력 범위 검색", description = "특정 악기를 실력 범위 내에서 조회합니다.")
    @GetMapping(value = "/instruments/{instrument}/level/{min}/{max}", produces = "application/json")
    public ResponseEntity<List<UserInstrument>> getUserInstrumentsByLevelBetween(@PathVariable(value = "instrument") String instrument,
                                                                           @PathVariable(value = "min") Long min,
                                                                           @PathVariable(value = "max") Long max){
        List<UserInstrument> userInstruments = userService.getUserInstrumentsByLevelBetween(instrument, min, max);

        log.info("UserInstrument: {}", userInstruments);

        return ResponseEntity.ok().body(userInstruments);
    }
}
