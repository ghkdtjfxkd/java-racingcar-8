package racingcar.entry.domain;

import racingcar.entry.strategy.PedalingStrategy;

class Engine {

    private static final int TORQUE = 1;
    private static final int REPULSIVE_OF_PEDAL = 4;

    private final PedalingStrategy pedaling;
    private Mileage mileage;

    private Engine(PedalingStrategy pedaling, Mileage mileage) {
        this.pedaling = pedaling;
        this.mileage = mileage;
    }

    static Engine setup(PedalingStrategy pedaling) {
        return new Engine(pedaling, Mileage.setup());
    }

    void movement() {
        if (pedaling.pushing() >= REPULSIVE_OF_PEDAL) {
            mileage = mileage.add(TORQUE);
        }
    }

    int mileage() {
        return mileage.distance();
    }
}
