package racingcar.entry;

import java.util.List;
import java.util.Map.Entry;
import racingcar.entry.strategy.PedalingStrategy;
import racingcar.entry.domain.RacingCarFactory;
import racingcar.entry.domain.RacingCars;

public class EntryServiceImpl implements EntryService {

    private final PedalingStrategy pedalingStrategy;
    private final RacingEntryRepository racingEntryRepository;

    public EntryServiceImpl(PedalingStrategy pedalingStrategy) {
        this.pedalingStrategy = pedalingStrategy;
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
    public List<Entry<String, Integer>> currentScores() {
        return carsInRace().status()
                .toList();
    }

    private RacingCars carsInRace() {
        return racingEntryRepository.getEntry();
    }

    private RacingCars carsFrom(List<String> carNames) {
        RacingCarFactory factory = RacingCarFactory.of(pedalingStrategy);
        return factory.createEntryFrom(carNames);
    }
}
