package postgresql.classic.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import postgresql.classic.constants.Instruments;
import postgresql.classic.domain.UserInstrument;
import postgresql.classic.dto.UserInstrumentDTO;
import postgresql.classic.repository.UserInstrumentRepository;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserInstrumentRepository userInstrumentRepository;

    private void isInstrumentExists(String instrument) {
        Instruments instruments = null;
        for(Instruments i : Instruments.values()) {
            if(i.name().equalsIgnoreCase(instrument)) {
                return;
            }
        }
        throw new RuntimeException("Instrument does not exist");
    }

    private String getInstrumentName(String instrument) {
        String instrumentName = instrument.toUpperCase();
        isInstrumentExists(instrumentName);
        return instrumentName;
    }

    public String addUserInstrument(UserInstrumentDTO dto) {
        log.info("UserService addUserInstrument");
        dto.setInstrument(getInstrumentName(dto.getInstrument()));

        UserInstrument userInstrument = UserInstrument.builder()
                .userId(dto.getUserId())
                .instrument(dto.getInstrument())
                .level(dto.getLevel())
                .build();


        // Later, add exception handling for non-existent user after implementing user login
        userInstrumentRepository.save(userInstrument);
        log.info("UserInstrument added successfully");
        return "Instrument added successfully";
    }

    public UserInstrument getUserInstrument(String userId, String instrument) {
        log.info("UserService getInstrument");
        String instrumentName = getInstrumentName(instrument);

        return userInstrumentRepository.findByUserIdAndInstrument(userId, instrumentName)
                .orElseThrow(() -> new RuntimeException("UserInstrument data not found"));
    }

    public List<UserInstrument> getUserInstruments(String userId){
        log.info("UserService getInstruments");
        return userInstrumentRepository.findAllByUserId(userId);
    }

    public List<UserInstrument> getUserInstrumentsByLevelBetween(String instrument, Long level1, Long level2) {
        log.info("UserService getUserInstrumentsByLevelBetween");
        String instrumentName = getInstrumentName(instrument);

        return userInstrumentRepository.findAllByInstrumentAndLevelBetween(instrumentName, level1, level2);
    }


}
