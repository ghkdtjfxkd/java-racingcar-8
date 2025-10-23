package racingcar.result.domain;

import java.util.List;
import java.util.Map.Entry;

public class FinalStandings {

    private final int winningPosition;
    private final List<String> winners;

    private FinalStandings(List<Entry<String,Integer>> currentStandings) {
        this.winningPosition = farthestPositionFrom(currentStandings);
        this.winners = identifyWinners(currentStandings);
    }

    public static FinalStandings of(List<Entry<String,Integer>> currentStandings) {
        return new FinalStandings(currentStandings);
    }

    public List<String> winners() {
        return List.copyOf(winners);
    }

    private int farthestPositionFrom(List<Entry<String,Integer>> currentStandings) {
        return currentStandings.stream()
                .map(Entry::getValue)
                .max(Integer::compareTo)
                .orElse(0);
    }

    private List<String> identifyWinners(List<Entry<String,Integer>> currentStandings) {
        return currentStandings.stream()
                .filter(this::isFarthest)
                .map(Entry::getKey)
                .toList();
    }

    private boolean isFarthest(Entry<String, Integer> racingCarScore) {
        return racingCarScore.getValue().equals(winningPosition);
    }
}
