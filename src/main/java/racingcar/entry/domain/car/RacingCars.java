package racingcar.entry.domain.car;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class RacingCars {

    private final List<RacingCar> entry;

    private RacingCars(List<RacingCar> entry) {
        this.entry = List.copyOf(entry);
    }

    public static RacingCars from(List<String> stream) {
        return new RacingCars(enrollCarsFrom(stream));
    }

    public void updateCarPositions() {
        entry.forEach(RacingCar::drive);
    }

    public Stream<Entry<String, Integer>> status() {
        return entry.stream()
                .map(racingcar -> Map.entry(racingcar.name(), racingcar.position()));
    }

    private static List<RacingCar> enrollCarsFrom(List<String> carNames) {
        return carNames.stream()
                .map(RacingCars::register)
                .toList();
    }

    private static RacingCar register(String CarName) {
        return RacingCar.by(CarName);
    }
}
