package racingcar.dto.response;

public record CarPositionDto(String name, int position) {
    public static CarPositionDto of(String name, int position) {
        return new CarPositionDto(name, position);
    }
}
