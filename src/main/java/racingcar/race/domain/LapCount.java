package racingcar.race.domain;

class LapCount {

    private final int remaining;

    private LapCount(int lapCount) {
        this.remaining = lapCount;
    }

    static LapCount from(String input) {
        int count = parseToInt(input);

        requirePositive(count);
        return new LapCount(count);
    }

    private static int parseToInt(String lapCountInput) {
        try {
            return Integer.parseInt(lapCountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.IS_OUT_OF_INTEGER.getMessage());
        }
    }

    private static void requirePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_POSITIVE.getMessage());
        }
    }

    LapCount decrease() {
        return new LapCount(this.remaining - 1);
    }

    boolean hasMore() {
        return remaining > 0;
    }
}
