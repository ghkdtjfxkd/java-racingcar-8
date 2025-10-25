package racingcar.race.domain;

class LapCount {

    private final int remaining;

    private LapCount(int lapCount) {
        this.remaining = lapCount;
    }

    static LapCount from(String input) {
        return new LapCount(parse(input));
    }

    private static int parse(String lapCountInput) {
        requireNumeric(lapCountInput);
        try {
            int value = Integer.parseInt(lapCountInput);
            requirePositive(value);
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.IS_OUT_OF_INTEGER.getMessage());
        }
    }

    private static void requireNumeric(String lapCountInput) {
        for (char c : lapCountInput.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ErrorMessage.IS_NOT_DIGIT.getMessage());
            }
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
