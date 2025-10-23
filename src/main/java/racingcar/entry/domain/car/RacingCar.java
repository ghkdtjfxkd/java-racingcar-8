package racingcar.entry.domain.car;

class RacingCar {

    private final String name;
    private final Engine engine;

    private RacingCar(String name) {
        this.name = name;
        this.engine = Engine.setup();
    }

    static RacingCar by(String name) {
        return new RacingCar(name);
    }

    void drive() {
        engine.movement();
    }

    int position() {
        return engine.mileage();
    }

    String name() {
        return name;
    }
}
