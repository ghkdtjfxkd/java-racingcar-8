package racingcar.entry.domain;

import java.util.List;
import racingcar.entry.strategy.PedalingStrategy;

public class RacingCarFactory {

    private final PedalingStrategy pedalingStrategy;

    private RacingCarFactory(PedalingStrategy pedalingStrategy) {
        this.pedalingStrategy = pedalingStrategy;
    }

    public static RacingCarFactory of(PedalingStrategy pedalingStrategy) {
        return new RacingCarFactory(pedalingStrategy);
    }

    public RacingCars createEntryFrom(List<String> carNames) {
        return new RacingCars(createCarsFrom(carNames));
    }

    private List<RacingCar> createCarsFrom(List<String> carNames) {
        return carNames.stream()
                .map(this::create)
                .toList();
    }

    private RacingCar create(String name) {
        return RacingCar.from(name, tuningEngine());
    }

    private Engine tuningEngine() {
        return Engine.setup(pedalingStrategy);
    }
}
