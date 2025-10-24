package racingcar.dto;

import java.util.List;
import java.util.Map.Entry;

public record RaceStateResponse(List<Entry<String, Integer>> racingRecords) {
}
