package racingcar.registration;

import java.util.List;
import racingcar.registration.domain.Participants;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public List<String> registerParticipants(String input) {
        Participants participants = Participants.from(input);
        return participants.names();
    }
}
