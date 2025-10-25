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
        if(lapCountInput == null || lapCountInput.isBlank()) {
            throw new IllegalArgumentException("랩 값 입력 비어있음");
        }
    }
}
