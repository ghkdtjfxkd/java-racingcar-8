package racingcar.dto.request;

public record LapCountRequest(String lapCount) {
    public static LapCountRequest from(String lapCount) {
        return new LapCountRequest(lapCount);
    }
}
