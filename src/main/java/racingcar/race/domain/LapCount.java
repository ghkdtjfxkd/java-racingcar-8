package racingcar.race.domain;

class LapCount {

    private final int remaining;

    private LapCount(int count) {
        this.remaining = count;
    }

    static LapCount from(String input) {
        return new LapCount(parsePositiveInt(input));
    }

    static LapCount of(int count) {
        return new LapCount(count);
    }

    LapCount decrease() {
        return new LapCount(this.remaining - 1);
    }

    boolean hasMore() {
        return remaining > 0;
    }

    private static int parsePositiveInt(String lapCountInput) {
        try {
            int value = Integer.parseInt(lapCountInput);
            requirePositive(value);
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("int 범위 넘어가");
        }
    }

    private static void requirePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("0보단 커야 돼");
        }
    }
}
