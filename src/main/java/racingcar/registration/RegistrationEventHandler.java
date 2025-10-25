package racingcar.registration;

import java.util.List;
import racingcar.common.EventBus;
import racingcar.common.schema.InputEvents.UserEnteredParticipants;
import racingcar.common.schema.RegistrationEvents.ParticipantsValidated;

public class RegistrationEventHandler {

    private final EventBus eventBus;
    private final RegistrationService registrationService;

    public RegistrationEventHandler(EventBus eventBus, RegistrationService registrationService) {
        this.eventBus = eventBus;
        this.registrationService = registrationService;
        registerHandlers();
    }

    private void registerHandlers() {
        eventBus.subscribe(UserEnteredParticipants.class, this::handleUserEnteredParticipants);
    }

    private void handleUserEnteredParticipants(UserEnteredParticipants input) {
        List<String> names = registrationService.registerParticipants(input.names());
        eventBus.publish(new ParticipantsValidated(names));
    }
}
