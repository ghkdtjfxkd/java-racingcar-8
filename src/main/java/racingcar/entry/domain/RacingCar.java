package racingcar.entry.domain;

class RacingCar {

    private final String name;
    private final Engine engine;

    private RacingCar (String name, Engine engine) {
        this.name = name;
        this.engine = engine;
    }

    static RacingCar from(String name, Engine engine) {
        return new RacingCar(name, engine);
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
