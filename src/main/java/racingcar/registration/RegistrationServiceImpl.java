package racingcar.registration;

import java.util.List;
import racingcar.registration.domain.Participants;

public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationServiceImpl() {
        this.registrationRepository = new RegistrationRepository();
    }

    @Override
    public List<String> registerParticipants(String input) {
        Participants participants = Participants.from(input);
        registrationRepository.save(participants);
        return participants.names();
    }
}
