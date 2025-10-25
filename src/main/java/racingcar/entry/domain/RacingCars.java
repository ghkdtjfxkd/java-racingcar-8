package racingcar.entry.domain;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class RacingCars {

    private final List<RacingCar> entry;

    RacingCars(List<RacingCar> entry) {
        this.entry = List.copyOf(entry);
    }

    public void updateCarPositions() {
        entry.forEach(RacingCar::drive);
    }

    public Stream<Entry<String, Integer>> status() {
        return entry.stream()
                .map(racingcar -> Map.entry(racingcar.name(), racingcar.position()));
    }
}
