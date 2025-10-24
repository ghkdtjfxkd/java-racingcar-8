package racingcar.entry.domain;

class Engine {

    private static final int TORQUE = 1;

    private final PedalingStrategy pedaling;
    private Mileage mileage;

    private Engine(Mileage mileage) {
        this.mileage = mileage;
        this.pedaling = new RandomPedalingStrategy();
    }

    static Engine setup() {
        return new Engine(Mileage.setup());
    }

    void movement() {
        if (pedaling.isEffective()) {
            mileage = mileage.add(TORQUE);
        }
    }

    int mileage() {
        return mileage.getDistance();
    }
}
