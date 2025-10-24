package racingcar.entry.domain;

class Mileage {
    private static final int SETUP_MILEAGE = 0;

    private final int distance;

    private Mileage(int distance) {
        this.distance = distance;
    }

    static Mileage setup() {
        return new Mileage(SETUP_MILEAGE);
    }

    Mileage add(int torque) {
        return new Mileage(this.distance + torque);
    }

    public int getDistance() {
        return distance;
    }
}
