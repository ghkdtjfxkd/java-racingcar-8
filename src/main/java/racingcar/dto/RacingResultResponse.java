package racingcar.dto;

import java.util.List;

public record RacingResultResponse(List<String> winners) {
    public static RacingResultResponse of(List<String> winners) {
        return new RacingResultResponse(winners);
    }
}
