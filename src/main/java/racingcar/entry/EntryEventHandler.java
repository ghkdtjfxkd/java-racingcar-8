package racingcar.entry;

import racingcar.common.EventBus;
import racingcar.common.schema.EntryEvents;
import racingcar.common.schema.RegistrationEvents;

public class EntryEventHandler {

    private final EventBus eventBus;
    private final EntryService entryService;

    public EntryEventHandler(EventBus eventBus, EntryService entryService) {
        this.eventBus = eventBus;
        this.entryService = entryService;
        registerHandlers();
    }

    private void registerHandlers() {
        eventBus.subscribe(RegistrationEvents.ParticipantsValidated.class, this::handleParticipantsValidated);
    }

    private void handleParticipantsValidated(RegistrationEvents.ParticipantsValidated validated) {
        entryService.registerCars(validated.names());
        eventBus.publish(new EntryEvents.CarsPrepared());
    }
}
