package racingcar.entry;

import java.util.List;
import java.util.Map.Entry;
import racingcar.entry.domain.car.RacingCars;
import racingcar.entry.domain.register.Participants;
import racingcar.entry.dto.ScoreResponse;

public class EntryServiceImpl implements EntryService {

    private final RacingEntryRepository racingEntryRepository;

    private EntryServiceImpl() {
        this.racingEntryRepository = new RacingEntryRepository();
    }

    @Override
    public void registerCars(String carNamesInput) {
        racingEntryRepository.save(carsFrom(carNamesInput));
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

    private RacingCars carsFrom(String carNamesInput) {
        Participants participants = Participants.from(carNamesInput);
        return RacingCars.from(participants.names());
    }
}
