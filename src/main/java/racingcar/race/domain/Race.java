package racingcar.race.domain;

public class Race {

    private LapCount remainingLaps;

    private Race(String lapCount) {
        requireNonBlank(lapCount);
        this.remainingLaps = LapCount.from(lapCount);
    }

    public static Race from(String remainingLaps) {
        return new Race(remainingLaps);
    }

    public boolean hasRemainingLaps() {
        return remainingLaps.hasMore();
    }

    public void advanceLap() {
        if(remainingLaps.hasMore()) {
            remainingLaps = remainingLaps.decrease();
        }
    }

    private void requireNonBlank(String lapCountInput) {
        System.out.println("지나감");
        if(lapCountInput == null || lapCountInput.isBlank()) {
            System.out.println("에외 발생함");
            throw new IllegalArgumentException("랩 값 입력 비어있음");
        }
    }
}
