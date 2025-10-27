package racingcar.result.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FinalStandings {

    private final int winningPosition;
    private final List<String> winners;

    private FinalStandings(Map<String,Integer> currentStandings) {
        this.winningPosition = farthestPositionFrom(currentStandings.values());
        this.winners = identifyWinners(currentStandings);
    }

    public static FinalStandings of(Map<String,Integer> currentStandings) {
        return new FinalStandings(currentStandings);
    }

    public List<String> winners() {
        return List.copyOf(winners);
    }

    private int farthestPositionFrom(Collection<Integer> currentPositions) {
        return currentPositions.stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    private List<String> identifyWinners(Map<String, Integer> currentStandings) {
        return currentStandings.entrySet().stream()
                .filter(this::isFarthest)
                .map(Entry::getKey)
                .toList();
    }

    private boolean isFarthest(Entry<String, Integer> carStatus) {
        return carStatus.getValue() == winningPosition;
    }
}
