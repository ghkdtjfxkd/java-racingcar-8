package racingcar.race.domain;

public class Race {

    private LapCount remainingLaps;

    private Race(String lapCount) {
        requireValid(lapCount);
        this.remainingLaps = LapCount.from(lapCount);
    }

    public static Race from(String remainingLaps) {
        return new Race(remainingLaps);
    }

    public boolean hasRemainingLaps() {
        return remainingLaps.hasMore();
    }

    public void advanceLap() {
        if (remainingLaps.hasMore()) {
            remainingLaps = remainingLaps.decrease();
        }
    }

    private void requireValid(String lapCount) {
        requireNonBlank(lapCount);
        requireNumeric(lapCount);
    }

    private void requireNonBlank(String lapCountInput) {
        if (lapCountInput == null || lapCountInput.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.IS_BLANK.getMessage());
        }
    }

    private void requireNumeric(String lapCountInput) {
        for (char token : lapCountInput.toCharArray()) {
            requireDigit(token);
        }
    }

    private void requireDigit(char token) {
        if (!Character.isDigit(token)) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_DIGIT.getMessage());
        }
    }
}
