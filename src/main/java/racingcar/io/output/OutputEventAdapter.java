package racingcar.io.output;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import racingcar.common.EventBus;
import racingcar.common.schema.EntryEvents.FirstLapRacingCarsMoved;
import racingcar.common.schema.EntryEvents.RacingCarsMoved;
import racingcar.common.schema.OutputEvents.LapResultAnnounced;
import racingcar.dto.response.CarPositionDto;
import racingcar.dto.response.LapStateResponse;

// EDA 전용
public class OutputEventAdapter {

    private final EventBus eventBus;

    public OutputEventAdapter(EventBus eventBus) {
        this.eventBus = eventBus;
        registerHandlers();
    }

    private void registerHandlers() {
        eventBus.subscribe(FirstLapRacingCarsMoved.class, this::handleFirstLapRacingCarMoved);
        eventBus.subscribe(RacingCarsMoved.class, this::handleRacingCarsMoved);
    }

    private void handleFirstLapRacingCarMoved(FirstLapRacingCarsMoved firstLapRacingCarsMoved) {
        OutputView.announceExecutionHeader();
        broadcastLapResult(adapted(firstLapRacingCarsMoved.carsPositions()));
    }

    private void handleRacingCarsMoved(RacingCarsMoved racingCarsMoved) {
        broadcastLapResult(adapted(racingCarsMoved.carsPositions()));
    }

    private void broadcastLapResult(List<CarPositionDto> carPositions) {
        OutputView.announce(LapStateResponse.of(carPositions));
        eventBus.publish(new LapResultAnnounced());
    }

    private List<CarPositionDto> adapted(Map<String, Integer> positions) {
        return positions.entrySet().stream()
                .map(this::toCarPositionDto)
                .toList();
    }

    private CarPositionDto toCarPositionDto(Entry<String, Integer> status) {
        return CarPositionDto.of(status.getKey(), status.getValue());
    }
}
