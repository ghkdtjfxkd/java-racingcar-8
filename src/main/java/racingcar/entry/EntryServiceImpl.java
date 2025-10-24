package racingcar.entry;

import java.util.List;
import java.util.Map.Entry;
import racingcar.entry.domain.car.RacingCars;
import racingcar.entry.dto.ScoreResponse;

public class EntryServiceImpl implements EntryService {

    private final RacingEntryRepository racingEntryRepository;

    private EntryServiceImpl() {
        this.racingEntryRepository = new RacingEntryRepository();
    }

    @Override
    public void registerCars(List<String> carNames) {
        racingEntryRepository.save(carsFrom(carNames));
    }

    @Override
    public void executeRound() {
        carsInRace().updateCarPositions();
    }

    @Override
    public List<ScoreResponse> currentScores() {
        return carsInRace().status()
                .map(this::score)
                .toList();
    }

    private ScoreResponse score(Entry<String, Integer> lapScore) {
        return ScoreResponse.of(lapScore.getKey(), lapScore.getValue());
    }

    private RacingCars carsInRace() {
        return racingEntryRepository.getEntry();
    }

    private RacingCars carsFrom(List<String> carNames) {
        return RacingCars.from(carNames);
    }
}
