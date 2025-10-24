package racingcar.entry;

import java.util.List;
import racingcar.entry.dto.ScoreResponse;

public interface EntryService {
    void registerCars(List<String> carNames);
    void executeRound();
    List<ScoreResponse> currentScores();
}
