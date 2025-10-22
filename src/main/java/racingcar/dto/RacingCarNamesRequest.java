package racingcar.dto;

public record RacingCarNamesRequest(String rawInput) {
    public static RacingCarNamesRequest from(String rawInput) {
        return new RacingCarNamesRequest(rawInput);
    }
}
