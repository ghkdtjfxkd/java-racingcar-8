package racingcar.io.output;

import java.util.List;
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
        OutputView.announceExecuteHeader();
        List<CarPositionDto> carPositions = toCarPositionDtos(firstLapRacingCarsMoved);
        broadcastLapResult(carPositions);
    }

    private void handleRacingCarsMoved(RacingCarsMoved racingCarsMoved) {
        List<CarPositionDto> carPositions = toCarPositionDtos(racingCarsMoved);
        broadcastLapResult(carPositions);
    }

    private List<CarPositionDto> toCarPositionDtos(FirstLapRacingCarsMoved firstLapRacingCarsMoved) {
        return firstLapRacingCarsMoved.carsPositions().entrySet()
                .stream()
                .map(this::toCarPositionDto)
                .toList();
    }

    private List<CarPositionDto> toCarPositionDtos(RacingCarsMoved RacingCarsMoved) {
        return RacingCarsMoved.carsPositions().entrySet()
                .stream()
                .map(this::toCarPositionDto)
                .toList();
    }

    private CarPositionDto toCarPositionDto(Entry<String, Integer> status) {
        return CarPositionDto.of(status.getKey(), status.getValue());
    }

    private void broadcastLapResult(List<CarPositionDto> carPositions) {
        OutputView.announce(LapStateResponse.of(carPositions));
        eventBus.publish(new LapResultAnnounced());
    }
}
