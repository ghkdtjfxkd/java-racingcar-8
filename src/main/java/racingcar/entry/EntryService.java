package racingcar.entry;

import java.util.List;
import java.util.Map.Entry;

public interface EntryService {
    void registerCars(List<String> carNames);
    void executeRound();
    List<Entry<String, Integer>> currentScores();
}
