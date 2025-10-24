package racingcar.io.output;

import racingcar.common.EventBus;

public class OutputEventAdapter {

    private final EventBus eventBus;

    public OutputEventAdapter(EventBus eventBus) {
        this.eventBus = eventBus;
        registerHandlers();
    }

    private void registerHandlers() {

    }
}
