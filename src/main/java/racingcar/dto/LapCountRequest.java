package racingcar.dto;

public record LapCountRequest(String lapCount) {
    public static LapCountRequest from(String lapCount) {
        return new LapCountRequest(lapCount);
    }
}
