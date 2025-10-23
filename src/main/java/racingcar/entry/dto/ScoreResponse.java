package racingcar.entry.dto;

public record ScoreResponse(String carName, int score) {
    public static ScoreResponse of(String carName, int score) {
        return new ScoreResponse(carName, score);
    }
}
