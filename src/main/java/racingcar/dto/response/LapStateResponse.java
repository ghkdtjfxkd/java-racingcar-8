package racingcar.dto.response;

import java.util.List;

public record LapStateResponse(List<CarPositionDto> racingRecords) {
    public static LapStateResponse of(List<CarPositionDto> racingRecords) {
        return new LapStateResponse(racingRecords);
    }
}
